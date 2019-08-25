import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authentication = this.authenticationService.currentUserValue;

    if (authentication && authentication.token) {
      request = request.clone({ setHeaders: { Authorization: authentication.token }});
    }

    return next.handle(request);
  }

}
