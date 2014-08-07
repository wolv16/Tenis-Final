/**
 * @(#)TenisModelo.java
 *
 *
 * @author 
 * @version 1.00 2014/7/17
 */


public class TenisModelo {

	private int puntosA;
	private int puntosB;
	private String nombreA;
	private String nombreB;
	Idioma idioma;
	private boolean juegoTeminado;

    public TenisModelo(String nombreA,String nombreB) {
    	this.nombreA = nombreA;
    	this.nombreB = nombreB;
    	puntosA = 0;
    	puntosB = 0;
    	juegoTeminado = false;
    }

    public void puntoA(){
    	puntosA++;
    }

    public void puntoB(){
    	puntosB++;    	
    }

    public String juega(){
        String mensaje ="";
        //mensajes menor a 40 puntos
        if (puntosA == puntosB){
            if (puntosA <= 2 || puntosB < 2){
                if(puntosA == 1){
                        mensaje = idioma.get15()+" - "+idioma.getAll(); //15 all
                }
                if(puntosA == 2){
                        mensaje = idioma.get30()+" - "+idioma.getAll(); //30 all
                }   
            }
            else{
                    mensaje = idioma.getDeuce(); //deuce
            }
        }
        else{
            //parte de Jugador 1
            if(puntosA == 0){
                    mensaje = idioma.getLove ()+" - ";// love
            }
            if(puntosA == 1){
                    mensaje = idioma.get15()+" - ";//15
            }
            if(puntosA == 2){
                    mensaje = idioma.get30()+" - ";//30
            }
            if(puntosA == 3){
                    mensaje = idioma.get40()+" - ";//40
            }
            if ((puntosB > 3) && (puntosB > puntosA)){
                    mensaje = idioma.getAdvantage()+nombreB;//player dos ventaja
            }
            //parte de Jugador 2
            if(puntosB == 0){
                            mensaje += idioma.getLove();
                    }
            if(puntosB == 1){
                            mensaje += idioma.get15();
                    }
            if(puntosB == 2){
                    mensaje += idioma.get30();
            }
            if(puntosB == 3){
                    mensaje += idioma.get40();
            }
            if ((puntosA > 3) && (puntosA > puntosB)){
                    mensaje = idioma.getAdvantage()+nombreA;
            }
        }
        // ventaja 4-0,4-1,4-2
        int diferencia = puntosA-puntosB;
        if (diferencia >= 2 && puntosA >= 4){ //Gana A            
            mensaje = nombreA+idioma.getWin();
            actualizarEstadoJuego();
        }
        
        if (diferencia <= -2 && puntosB >= 4){ //Gana B
            mensaje = nombreB+idioma.getWin();
            actualizarEstadoJuego();
        }        
        //jLabel2.setText(mensaje);
        //jLabel4.setText(mensaje);
        return mensaje;
    }

    public void actualizarEstadoJuego(){
    	juegoTeminado = true;
    }

    public boolean estadoJuego(){
    	return juegoTeminado;
    }

    public int getPuntosA(){
		return puntosA;
    }

	public int getPuntosB(){
		return puntosB;
    }

    public String getNombreA(){
    	return nombreA;
    }

    public String getNombreB(){
    	return nombreB;
    }

    public void reiniciaPuntosA(){
    	this.puntosA = 0;
    }

	public void reiniciaPuntosB(){
    	this.puntosB = 0;
    }

    public void setPuntosA(int puntosA){
    	this.puntosA = puntosA;
    }

    public void setPuntosB(int puntosB){
    	this.puntosB = puntosB;	
    }

    public void setIdioma(Idioma idioma){
    	this.idioma = idioma;
    }
    
    public void restaurarEstadoJuego(){
        juegoTeminado=false;
        
    }


}