import { Usuario } from './Usuario';
import { Repuesto } from './Repuesto';
import { Bus } from './Bus';

/**
 * Modelo que representa una solicitud de repuesto en el sistema
 *
 * @property idSolicitudRepuesto - Identificador único de la solicitud
 * @property fecha - Fecha de creación de la solicitud (formato YYYY-MM-DD)
 * @property cantidad - Cantidad solicitada del repuesto
 * @property motivo - Justificación de la solicitud
 * @property estado - Estado actual (Pendiente/Aprobado/Rechazado)
 * @property usuario - Usuario que realiza la solicitud
 * @property repuesto - Repuesto solicitado
 * @property bus - Bus asociado a la solicitud
 */
export class SolicitudRepuesto {
  constructor(
    public idSolicitudRepuesto: number,
    public fecha: string,
    public cantidad: number,
    public motivo: string,
    public estado: 'Pendiente' | 'Aprobado' | 'Rechazado',
    public usuario: Usuario,
    public repuesto: Repuesto,
    public bus: Bus
  ) {}
}

/**
 * DTO para creación/actualización de solicitudes (sin relaciones completas)
 */
export class SolicitudRepuestoDTO {
  constructor(
    public idSolicitudRepuesto?: number,
    public fecha?: string,
    public cantidad?: number,
    public motivo?: string,
    public estado?: 'Pendiente' | 'Aprobado' | 'Rechazado',
    public idRepuesto?: number,
    public idUsuario?: number,
    public idBus?: number,
    public placa?: string
  ) {}
}
