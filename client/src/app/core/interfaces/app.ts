export interface AppResponse<T> {
  message: string,
  status: string,
  content: T
}
