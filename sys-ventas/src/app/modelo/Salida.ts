export class Salida {
  constructor(
    public id: number,
    public idRepuesto: number,      
    public cantidadEntregada: number,
    public destinatario: string,
    public codigo: string,
    public fechaSalida: Date,
    public estado: string,
    public nombreRepuesto?: string  
  ) {}
}


