package Interfaces;

import java.util.List;

import Entidades.Entidad;


/**
 * 
 */
public interface Combinable {

    /**
     * @param Entidad e 
     * @return
     */
    public boolean combina(Entidad e, List<Entidad> l);

    /**
     * 
     */
    public List<Entidad> romper();

  

    /**
     * @return
     */
    public boolean esMovible();

}