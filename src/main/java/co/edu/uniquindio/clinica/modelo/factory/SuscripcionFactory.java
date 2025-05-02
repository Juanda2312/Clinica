package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionFactory {

    public static Suscripcion seleccionarSuscripcion(String suscripcion){
        return switch (suscripcion) {
            case ("Suscripción básica") -> SuscripcionBasica.getInstancia();
            case ("Suscripción premium") -> SuscripcionPremium.getInstancia();
            case ("Suscripción Premium Plus") -> SuscripcionPremiumPlus.getInstancia();
            default -> null;
        };
    }
}
