package br.com.battlepassCalculatorValorant.model.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import java.util.*
import kotlin.collections.ArrayList

class DbTierInput(val context: Context) : MyDataBase(context) {

    companion object {
        private const val TABELA = "historicInputs"
        private const val TIER = "tier"
        private const val DATE = "date"
        private const val EXP_MISSING = "expMissing"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = ("CREATE TABLE " + TABELA + " ("
                + TIER + " integer primary key, "
                + DATE + " integer, "
                + EXP_MISSING + " integer "
                + ") ")
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA)
        onCreate(db)
    }


    fun removeAll() {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TABELA, null, null)
    }

    // SQL Insert Data function

    fun insertData(tier: UserInputsTier): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        val tierCurrent = tier.tierCurrent
        val tierExpMissing = tier.tierExpMissing
        val tierTimeInMillis = tier.date.timeInMillis

        contentValues.put(TIER, tierCurrent)
        contentValues.put(DATE, tierTimeInMillis)
        contentValues.put(EXP_MISSING, tierExpMissing)

        val insert_data = db.insert(TABELA, null, contentValues)
        db.close()

        return !insert_data.equals(-1)
    }

    fun readData(): List<UserInputsTier> {
        val db = this.writableDatabase
        val data = db.rawQuery("SELECT * FROM $TABELA", null)
        val listInputs = ArrayList<UserInputsTier>()

        if (data != null && data.count > 0) {
            while (data.moveToNext()) {
                val tierIndex = data.getInt(0)
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = data.getLong(1)
                val tierExpMissing = data.getInt(2)
                val userInput = UserInputsTier(tierIndex, tierExpMissing, calendar)
                listInputs.add(userInput)
            }
        }

        db.close()
        return listInputs
    }

    fun deleteData(tier: String): Boolean {
        val db = this.writableDatabase
        val delete_data = db.delete(TABELA, "$TIER=?", arrayOf(tier))
        db.close()

        return !delete_data.equals(-1)
    }

    fun updateData(tierInputsTier: UserInputsTier): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TIER, tierInputsTier.tierCurrent)
        contentValues.put(EXP_MISSING, tierInputsTier.tierExpMissing)
        contentValues.put(DATE, tierInputsTier.date.timeInMillis)
        val update_data = db.update(
            TABELA, contentValues, "$TIER=?",
            arrayOf(tierInputsTier.tierCurrent.toString())
        )
        db.close()

        return !update_data.equals(-1)
    }

}