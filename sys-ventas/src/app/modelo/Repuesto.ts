export class Repuesto {
  id: any;
  constructor(
    public idRepuesto: number,
    public nombre: string,
    public stockActual: string,
    public codigo: string,
    public ubicacion: string,
    public estado: string,
    public stockMinimo: string
  ) {}
}
