import BuddySystem.*

fun potenciaDeDos(numero : Int) : Boolean {
  //La funcion potenciaDeDos verifica que un numero sea potencia o no de 2 y retorna el true en caso de serlo y false si no.
  if(numero==0)
    return false
  var n = numero.toDouble()
  return (Math.ceil((Math.log(n) / Math.log(2.0)))) == (Math.floor(((Math.log(n) / Math.log(2.0)))))
}

fun main() {
  println("Bienvenido al Buddy System!")
  println("Inserta la cantidad de bloques de memoria: ")
  var cantidadBloques = try { readLine()?.toInt() ?: -1 } catch (e: NumberFormatException) { -1 }
  if (cantidadBloques == -1 || !potenciaDeDos(cantidadBloques)){
    println("La cantidad de bloques debe ser un numero entero, potencia de 2")
  }
  else{
    var procesar = true
    var buddy = BuddySystem(cantidadBloques)
    while (procesar){
      var lectura = readLine() ?: "Nada"  //El operador Elvis garantiza que no se tomen valores nulos, si los hay toman el valor "Nada"
      var listaLectura = lectura.split(" ") //que eventualmente no tiene ningun efecto en la entrada
      if (listaLectura[0] == "RESERVAR"){
        if (listaLectura.size == 3){  //Las reservas solo pueden tener hasta 3 argumentos, RESERVAR id valor
          buddy.reservar(listaLectura[1],listaLectura[2].toInt())
        }
        else{
          println("La expresion no esta definida.")
        }
      }
      else if (listaLectura[0] == "LIBERAR"){
        if (listaLectura.size == 2){ //La liberacion de identificadores solo puede tener 2 argumentos, LIBERAR id
          buddy.liberar(listaLectura[1])
        }
        else{
          println("La expresion no esta definida.")
        }
      }
      else if (listaLectura[0] == "MOSTRAR"){
        if (listaLectura.size == 1){ //La muestra de los bloques en consola solo tiene un argumento, MOSTRAR
          buddy.mostrar()
        }
        else{
          println("La expresion no esta definida.")
        }
      }
      else if (listaLectura[0] == "SALIR"){
        procesar = false
      }
      else{
        if (lectura != ""){
          println("La expresion no esta definida.")
        }        
      }
    }
  }
  println("Has salido del Buddy System")
}
