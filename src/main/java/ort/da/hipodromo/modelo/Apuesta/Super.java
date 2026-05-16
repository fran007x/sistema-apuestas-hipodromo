package ort.da.hipodromo.modelo.Apuesta;

public class Super extends ModalidadApuesta {

    public Super() {
        super("Super");
    }

    @Override 
    public double calcularCosto(double monto){
        return monto * 2;
    }

    @Override
    public double calcularPago(double monto,double dividendoFinal, double totalApostadoRegistro){
        if(dividendoFinal >= 2){
            return monto * dividendoFinal * 3;
        }
        return monto * dividendoFinal * 4;
    }
}