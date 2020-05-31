package intro;

public class Articulo {
    String nombre;
    int precio;
    double numEstrellas;
    double relacionCalidadPrecio;


    public Articulo(String nombreArticulo, int precioArticulo, double califArticulo) {
        this.nombre = nombreArticulo;
        this.precio = precioArticulo;
        this.numEstrellas = califArticulo;
        this.relacionCalidadPrecio = (numEstrellas/precio) * 1000000;
    }

    public boolean esMejor(Articulo mejorArticulo) {
        if(this.relacionCalidadPrecio > mejorArticulo.relacionCalidadPrecio)
            return true;
        else
            return false;
    }

}