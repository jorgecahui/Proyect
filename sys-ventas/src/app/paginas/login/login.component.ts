import { Component } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { AuthService } from '../../servicio/auth.service';
import { Router, RouterLink } from '@angular/router';
import { environment } from '../../../environments/environment.development';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user: string = '';
  clave: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login() {
    if (!this.user || !this.clave) return; // Validación rápida de campos

    this.authService.login(this.user, this.clave).subscribe({
      next: (data) => {
        console.log('Login exitoso', data);
        sessionStorage.setItem(environment.TOKEN_NAME, data.token);
        sessionStorage.setItem(environment.DATA_USERLOGIN, data.idUsuario.toString());
        this.router.navigate(['/pages/dashboard']);
      },
      error: (err) => {
        console.error('Error al iniciar sesión', err);
        // Aquí podrías mostrar un mensaje visual al usuario
      }
    });
  }
}
