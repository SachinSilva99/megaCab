package org.example.demo1.annotations;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo1.controller.CarController;
import org.example.demo1.controller.UserController;
import org.example.demo1.exception.ExceptionHandlerRegistry;
import org.example.demo1.exception.GlobalExceptionHandler;
import org.example.demo1.util.ResponseDTO;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    private final Map<String, Method> requestMappings = new HashMap<>();
    private final Map<String, Object> controllers = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        super.init();
        registerController(new UserController());
        registerController(new CarController());
        ExceptionHandlerRegistry.registerExceptionHandler(new GlobalExceptionHandler());  // Register exception handlers

    }

    private void registerController(Object controller) {
        controllers.put(controller.getClass().getSimpleName(), controller);

        if (controller.getClass().isAnnotationPresent(RequestMapping.class)) {
            RequestMapping classMapping = controller.getClass().getAnnotation(RequestMapping.class);
            for (Method method : controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                    String fullPath = classMapping.value() + methodMapping.value();
                    String methodType = methodMapping.method().name();
                    requestMappings.put(methodType + ":" + fullPath, method);
                }
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String method = req.getMethod();

        Method handlerMethod = requestMappings.get(method + ":" + path);
        if (handlerMethod == null) {
            sendErrorResponse(resp, ResponseDTO.error("No handler found for " + path));
            return;
        }

        try {
            Object controller = controllers.get(handlerMethod.getDeclaringClass().getSimpleName());
            Parameter[] parameters = handlerMethod.getParameters();
            Object[] paramValues = new Object[parameters.length];

            int index = 0;
            for (Parameter parameter : parameters) {
                if (parameter.isAnnotationPresent(RequestBody.class)) {
                    InputStream inputStream = req.getInputStream();
                    paramValues[index] = objectMapper.readValue(inputStream, parameter.getType());
                }
                index++;
            }

            handlerMethod.setAccessible(true);
            Object result = handlerMethod.invoke(controller, paramValues);

            sendSuccessResponse(resp, result);
        } catch (Exception e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            ResponseDTO<Object> errorResponse = ExceptionHandlerRegistry.handleException(cause);
            sendErrorResponse(resp, errorResponse);
        }
    }

    private void sendSuccessResponse(HttpServletResponse resp, Object result) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(ResponseDTO.success(result)));
    }

    private void sendErrorResponse(HttpServletResponse resp, ResponseDTO<Object> errorResponse) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(500);
        resp.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}