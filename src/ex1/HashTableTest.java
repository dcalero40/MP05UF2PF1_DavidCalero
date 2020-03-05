package ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable=new HashTable();
    @Test
    void size() {
    }

    @Test
    void realSize() {
    }

    @Test
    void put() {
//        vacio vacio
        Assertions.assertEquals("", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(0,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());

        //añadiendo 1
        hashTable.put("1", "hola");
        Assertions.assertEquals("\n bucket[1] = [1, hola]", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(1,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());

        //añadiendo con clave y valor distinto para ver si lo coloca en otra posiccion
        hashTable.put("2", "2");
        Assertions.assertEquals("\n bucket[1] = [1, hola]\n bucket[2] = [2, 2]", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(2,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());


        //añadiendo con clave igual para ver si cambia el valor. Veo que lo añade, pero deberia cambiar el valor para no repetir las clave(solucionado)
        hashTable.put("2", "pepe");
        Assertions.assertEquals("\n bucket[1] = [1, hola]\n bucket[2] = [2, pepe]", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(2,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());


        //meter una mas con clave distinta pero mismo hash
        hashTable.put("13", "juan");
        Assertions.assertEquals("\n bucket[1] = [1, hola]\n bucket[2] = [2, pepe] -> [13, juan]", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(3,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());

        //comprobar si cambia valor para la misma key en una posicion que no sea la primera
        hashTable.put("13", "tete");
        Assertions.assertEquals("\n bucket[1] = [1, hola]\n bucket[2] = [2, pepe] -> [13, tete]", hashTable.toString());
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(3,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());

        //compruebo el size de la has table, deberia ser 3 (encuentre primer error, no augmenta el size del hasTable) se arregla añadiendo size++
        Assertions.assertEquals(3,hashTable.size());
    }

    @Test
    void get() {
        //compruebo si devuelve el valor correspondiente a la clave
        hashTable.put("2", "a");
        Assertions.assertEquals("a",hashTable.get("2"));
        //compruebo que sucede si hago un get de una clave que tiene dos posibles valores (sobreescribe bien el put)
        hashTable.put("2", "b");
        Assertions.assertEquals("b",hashTable.get("2"));
        //compruebo si al colisionar recorre para buscarme el valor de la clave
        hashTable.put("13", "z");
        Assertions.assertEquals("z",hashTable.get("13"));
        //intentar obtener una clave que no existe
        Assertions.assertEquals(null,hashTable.get("3"));
        //intentar acceder a una clave que colisiona pero no existe
        Assertions.assertEquals(null,hashTable.get("35"));
    }

    @Test
    void drop() {
        //compruebo si elimina cuando hay un solo elemento
        hashTable.put("2", "a");
        hashTable.drop("2");
            //compruebo si acecta al size o al realsize
            Assertions.assertEquals(0,hashTable.size());
            //compruebo si acecta al realsize o al realsize
            Assertions.assertEquals(16,hashTable.realSize());
        Assertions.assertEquals(null,hashTable.get("2"));
        //compruebo si elimina cuando hay dos elemento de diferentes buckets.
        hashTable.put("2", "a");
        hashTable.put("3", "a");
        hashTable.drop("3");
        Assertions.assertEquals(null,hashTable.get("3"));

        //borro elemento que no existe en una colision
        hashTable.put("13", "b");
        hashTable.drop("35");
        Assertions.assertEquals("\n bucket[2] = [2, a] -> [13, b]",hashTable.toString());
        //compruebo si elimina en una colision (no elimina en una colision)

    }
}
