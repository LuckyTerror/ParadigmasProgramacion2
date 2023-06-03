package com.pp2;

public class App {
	//Declaración de los atributos de la clase:
	private static double[] distancias = {5.30, 5.73, 6.77, 5.26, 4.33, 5.45, 6.09, 5.64, 5.81, 5.75};
    private static double mediaAritmetica;
    private static double[] desviaciones;
    
    public synchronized static void calcularMedia() {
        mediaAritmetica = calcularMediaAritmetica(distancias);
        double mediaRedondeada = Math.round(mediaAritmetica * 100.0) / 100.0;		//Se redondea a 2 decimales la media obtenida
        System.out.println("Media Aritmética: " + mediaRedondeada);
    }

    public synchronized static void calcularDesviaciones() {
        desviaciones = calcularDesviaciones(distancias, mediaAritmetica);
        System.out.println("Desviaciones:");
        for (double desviacion : desviaciones) {									//Se utiliza un bucle "for-each" que simplifica el proceso de iterar sobre una
        	double desviacionRedondeada = Math.round(desviacion * 100.0) / 100.0;	//colección de elementos, aquí se utiliza para imprimir cada desviación en una
        	System.out.println(desviacionRedondeada);								//línea separada.
        }																			
    }																				//En la linea 18 se redondean a 2 decimales cada desviacion obtenida

    public static void main(String[] args){
        Thread mediaThread = new Thread(new Runnable() {	//Se declara la variable "mediaThread" como una instancia de la clase "Thread".
            @Override										//"@Override" define el código que se ejecutará cuando el hilo se vaya a ejecutar.
            public void run() {								//Dentro del método "run()", se llama al método "calcularMedia()",
                    calcularMedia();						//esto significa que cuando el hilo se inicie, se ejecutará el código del método "calcularMedia()".
                }
        });

        Thread desviacionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    calcularDesviaciones();
                }
        });

        mediaThread.start();				//Esta línea inicia la ejecución del hilo "mediaThread". Al llamar al método "start()",
        try {								//se crea un nuevo hilo y se ejecuta el código dentro del método "run()" asociado a ese hilo.
        	mediaThread.join();				//El método "join()" bloquea la ejecución del hilo principal hasta que el hilo al que se llama complete su ejecución.
        } catch(InterruptedException e) {	//La estructura "try-catch" se utiliza para manejar cualquier posible excepción que pueda 
        	e.printStackTrace();			//ocurrir durante la ejecución del método "join()". Si se produce una interrupción "(InterruptedException)",
        }									//se imprime la traza de la pila de errores utilizando "e.printStackTrace()" para ayudar en la depuración.
        

        desviacionThread.start();
        try {
        	desviacionThread.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
    }

    private static double calcularMediaAritmetica(double[] distancias) {
        double suma = 0;
        for (double distancia : distancias) {		//Se utiliza un bucle "for-each" que simplifica el proceso de iterar sobre una
            suma += distancia;						//colección de elementos, aquí se utiliza para que realice la sumatoria de las distancias.
        }
        return suma / distancias.length;			//Aquí se calcula la media de acuerdo al número de elementos del arreglo dada por "distancias.length"
    }

    private static double[] calcularDesviaciones(double[] distancias, double mediaAritmetica) {
        double[] desviaciones = new double[distancias.length];	//Se crea una matriz unidimensional donde se guardará los resultados de las desviaciones, por ende, tendrá en mismo tamaño que el arreglo de distancias.
        for (int i = 0; i < distancias.length; i++) {			//Se itera a través de cada elemento del arreglo distancias utilizando un bucle for
            desviaciones[i] = distancias[i] - mediaAritmetica;	//y se calcula la desviación restando el valor de mediaAritmetica a cada elemento del arreglo distancias
        }														//el resultado se asigna al correspondiente elemento del arreglo desviaciones.
        return desviaciones;
    }
}