import java.util.*;

//La idea es utilizar un HashMap, que sea de tipo Character, TNodoTrie, que representa el enlace a cada nodo, mientras que el valor
//será una referencia a los hijos

//No hace falta tener un espacio definido del trie
public class TNodoTrieHashMap {
    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;

    //Integer para la cantidad de palabras que debe almacenar el trie.
    //Debido a la utilización de un HashMap, no es necesario un índice, ya que no necesito saber en la posición en la que estoy parado.

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
    }

    public void insertar(String unaPalabra) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++ ) {
            char character = unaPalabra.charAt(c);
            nodo.hijos.putIfAbsent(character,new TNodoTrieHashMap());
            nodo = nodo.hijos.get(character);
        }
        nodo.esPalabra = true;
    }


    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for(Map.Entry<Character, TNodoTrieHashMap> entry : nodo.hijos.entrySet()) {
                imprimir(s + entry.getKey(), entry.getValue());
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrieHashMap buscarNodoTrie(String s) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < s.length(); c++) {
            char character = s.charAt(c);
            if(nodo == null) {
                return null;
            }
            nodo = nodo.hijos.get(character);
        }
        return nodo;
    }

    /**
     * Busca una palabra en el trie y devuelve la cantidad de comparaciones realizadas durante la búsqueda.
     *
     * @param s La palabra a buscar dentro del trie.
     * @return El número de comparaciones realizadas para determinar si la palabra está o no en el trie.
     */
    public int buscar(String s) {
        TNodoTrieHashMap nodo = this;
        int comparaciones = 0;
        for (int c = 0; c < s.length(); c++) {
            char character = s.charAt(c);
            nodo = nodo.hijos.get(character);
            comparaciones++;
            if(nodo == null) {
                return comparaciones;
            }
        }
        return nodo.esPalabra ? comparaciones : -1;
    }

    private void predecir(String s, LinkedList<String> palabras, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
            for(Map.Entry<Character, TNodoTrieHashMap> entry : nodo.hijos.entrySet()) {
                if(entry.getKey() != null) {
                    predecir(s + entry.getKey(), palabras, entry.getValue());
                }
            }
        }
    }

    /**
     * Genera una lista de todas las palabras que contienen el prefijo dado.
     * Las palabras encontradas se agregan a la lista 'palabras'.
     *
     * @param prefijo El prefijo que se utilizará para buscar palabras en el trie.
     * @param palabras Lista de strings donde se almacenarán las palabras encontradas que coinciden con el prefijo dado.
     */
    public void predecir(String prefijo, List<String> palabras) {
        TNodoTrieHashMap nodo = buscarNodoTrie(prefijo);
        if (nodo != null) {
            predecir(prefijo, (LinkedList<String>) palabras, nodo);
        }
    }
}
