//Implementacion de un Buddy System con listas mutables que almacenan los espacios libres y consumidos.
package BuddySystem
public class BuddySystem(cantidad : Int) {
    var tamano : Int = 0
    var espaciosLibres : MutableList<Int> = mutableListOf() //Espacios libres de memoria
    var espaciosConsumidos : MutableList<Pair<String,Int>> = mutableListOf()  //Espacios de memoria utilizados por algun identificador
    init {
        this.tamano = cantidad
        this.espaciosLibres.add(cantidad)
    }
    fun recibirEntradaPorConsola(){
		println("Recibe entrada")
	}
	fun buscarEnLista(nombre : String , lista : MutableList<Pair<String,Int>>) : Int {
		//La funcion buscarEnLista se encarga de conseguir si una lista mutable cuenta con un par especifico dentro de ella
		var objeto : Pair<String,Int>? = lista.find { it.first == nombre }
		var salida : Int
		if (objeto != null){
			salida = lista.indexOf(objeto)
		}
		else{
			salida = -1
		}
		return salida
	}
	fun reservar(nombre : String, cantidad : Int) : Boolean { //RESERVAR <nombre> <cantidad>
		//La funcion reservar toma el nombre de un identificador y verifica si ya existe en los espaciosConsumidos
		//Si no existe, busca el bloque de mejor tamano para alojar dicho identificador, considerando si es mas pequeno o
		//mas grande que los bloques libres que se encuentran en espaciosLibres.
		var salida = true
		if (buscarEnLista(nombre,this.espaciosConsumidos)!=-1){
			println("Ya existe el identificador ${nombre} en los bloques")
			salida = false
		}
		else{
			var ordenado : MutableList<Int> = this.espaciosLibres.sortedDescending().toMutableList()
			var notAssigned = true
			while (notAssigned){
				var busqueda : Int = ordenado.find { cantidad-1<it && it<=cantidad+1 } ?: -1
				if (ordenado.size ==0 || busqueda == -1 && ordenado[0] < cantidad){
					println("No es posible reservar un bloque de tamano ${cantidad}")
					salida = false
					notAssigned = false
				}
				else if (busqueda == -1 && ordenado[0]/2 < cantidad){
					this.espaciosConsumidos.add(Pair(nombre,ordenado[0]))
					ordenado.remove(ordenado[0])
					salida = true
					notAssigned = false
				}
				else if (busqueda == -1 && ordenado[0] > cantidad){
					ordenado.set(0, ordenado[0]/2)
					ordenado.add(ordenado[0])
					salida = false
				}
				else if (busqueda != -1 && ordenado[0] >= cantidad){
					ordenado.remove(busqueda)
					this.espaciosConsumidos.add(Pair(nombre,busqueda))
					salida = true
					notAssigned = false
				}
			}
			this.espaciosLibres = ordenado
		}
		return salida
	}
	fun liberar(nombre : String) : MutableList<Int> { //LIBERAR <nombre>
		//La funcion liberar elimina los bloques de espaciosConsumidos que tiene el identificador nombre asociados a ellos
		//Verifica si el nombre existe, en caso de no serlo lo indica en consola.
		//Si existe lo elimina de espaciosConsumidos y anade el espacio a los espaciosLibres.
		if (buscarEnLista(nombre,this.espaciosConsumidos)==-1){
			println("El identificador ${nombre} no tiene memoria reservada")
		}
		else{
			var objeto = this.espaciosConsumidos.find { it.first == nombre } ?: Pair("",0)
			this.espaciosConsumidos.remove(objeto)
			if (this.espaciosLibres.contains(objeto.second)){
				var indice = this.espaciosLibres.indexOf(objeto.second)
				this.espaciosLibres[indice] += this.espaciosLibres[indice]
			}
			else{
				this.espaciosLibres.add(objeto.second)
			}
			if (this.espaciosLibres.size != this.espaciosLibres.distinct().count()){
				this.unir()
			}
		}
		return this.espaciosLibres
	}
	fun unir(){
		//La funcion unir devuelve los bloques que partieron de la misma division a un unico tamano, 
		//luego de liberar el bloque faltante a la lista de bloques libres
		var nuevos : MutableList<Int> = mutableListOf()
		for (i in this.espaciosLibres) {
			if (nuevos.contains(i)){
				var indice = nuevos.indexOf(i)
				nuevos[indice] += nuevos[indice]
			}
			else{
				nuevos.add(i)
			}
		}
        this.espaciosLibres = nuevos
	}
	fun mostrar(){
		//La funcion mostrar imprime en consola los espacios libres y ocupados del buddy system
		println("Espacios Libres")
		for (espacios in this.espaciosLibres){
			println("|".repeat(espacios) + espacios.toString())
		}		
		println("Espacios Ocupados")
		for (ocupado in this.espaciosConsumidos){
			println("/".repeat(ocupado.second) + ocupado.second.toString() +" id = "+ ocupado.first)
		}
	}
}
