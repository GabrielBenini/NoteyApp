package com.gabrielbenini.noteyapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDB : RoomDatabase() {
    // Define a classe do banco de dados usando Room


    abstract val notesDao: NoteDao
    // Declara o DAO que fornece acesso às operações do banco

    companion object {
        // Cria um objeto estático que permite acessar o banco sem instanciar várias vezes


        @Volatile
        private var INSTANCE: NotesDB? = null
        // Armazena a instância única do banco; @Volatile evita problemas em multi-thread


        fun getInstance(context: Context): NotesDB {
            // Função que retorna a instância do banco (singleton)

            synchronized(this) {
                // Garante que apenas uma thread por vez crie ou acesse a instância


                var instance = INSTANCE
                // Cria uma variável temporária para verificar se já existe uma instância

                if (instance == null) {
                    // Se ainda não existe, cria uma nova instância do banco


                    instance = Room.databaseBuilder(
                        context = context.applicationContext, // Usa o contexto da aplicação (necessário para o Room)
                        NotesDB::class.java, // Define a classe que representa o banco de dados
                        "notes_db" // Nome do banco de dados
                    ).build()
                }
                INSTANCE = instance
                // Salva a instância criada para uso futuro


                return instance
                // Retorna a instância do banco (nova ou já existente)

            }
        }
    }
}