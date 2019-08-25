import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Authentication } from '../model';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private static readonly authenticationKey = 'currentAuthentication';
  private static readonly authorizationHeader = 'Authorization';
  private currentUserSubject: BehaviorSubject<Authentication>;

  public currentUser: Observable<Authentication>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Authentication>(JSON.parse(localStorage.getItem(AuthenticationService.authenticationKey)));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Authentication {
    return this.currentUserSubject.value;
  }

  login(email: string, password: string) {
    return this.http.post<any>('/api/login', { email, password }, { observe: 'response' })
      .pipe(map(response => {
        const token = response.headers.get(AuthenticationService.authorizationHeader);
        const authentication = new Authentication(token);

        localStorage.setItem(AuthenticationService.authenticationKey, JSON.stringify(authentication));
        this.currentUserSubject.next(authentication);

        return authentication;
      }));
  }

}
