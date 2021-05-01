package br.com.battlepassCalculatorValorant.database.SQLite

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

abstract class MyDataBase(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {
    companion object {
        private const val NOME_BANCO = "TierInputsHistory.db"
        private const val VERSAO = 4
    }
}