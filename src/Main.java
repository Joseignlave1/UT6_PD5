import java.util.*;

public class Main {
    public static void main(String[] args) {
        TNodoTrieHashMap trie = new TNodoTrieHashMap();
        trie.insertar("hola");
        trie.insertar("holanda");
        trie.insertar("hoyo");
        trie.insertar("hormiga");
        System.out.println(trie.buscar("hola"));
        System.out.println(trie.buscar("holanda"));
        System.out.println(trie.buscar("hoyo"));
        System.out.println(trie.buscar("hormiga"));
        System.out.println(trie.buscar("hormigon"));
        trie.imprimir();

        LinkedList<String> resultados = new LinkedList<>();
        trie.predecir("ho", resultados);
        System.out.println(resultados);
    }
}