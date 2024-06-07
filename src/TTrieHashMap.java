import java.util.LinkedList;
import java.util.List;
import java.util.*;


public class TTrieHashMap {
    private TNodoTrieHashMap raiz;

    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertar(palabra);
    }


    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }


    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        }
        return 0;
    }


    public List<String> predecir(String prefijo) {
        LinkedList<String> resultados = new LinkedList<>();
        if (raiz != null && prefijo != null && !prefijo.isEmpty()) {
            raiz.predecir(prefijo, resultados);
        }
        return resultados;
    }
}
