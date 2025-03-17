import { Injectable } from '@angular/core';

export const USER = "USER"

export interface LoggedInUser{
  token : string,
  username: string
}


@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }


  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getItem<T>(key: string): T | null {
    const data = localStorage.getItem(key);
    return data ? JSON.parse(data) as T : null;
  }

  removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  clear(): void {
    localStorage.clear();
  }

}
