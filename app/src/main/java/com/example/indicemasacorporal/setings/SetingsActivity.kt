package com.example.indicemasacorporal.setings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.indicemasacorporal.databinding.ActivitySetingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setings")

class SetingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_VIBRATION = "key_vibration"
    }

    private lateinit var binding: ActivitySetingsBinding
    private var firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect{ setingsData ->
                if (setingsData != null){
                    runOnUiThread{
                        binding.swichtOscuro.isChecked = setingsData.darkmode
                        binding.swichtBluetooth.isChecked = setingsData.bluetooth
                        binding.swichtMute.isChecked = setingsData.vibration
                        binding.rsVolumen.setValues(setingsData.volum.toFloat())
                        firstTime = !firstTime
                    }

                }

            }
        }
        initUI()
    }

    private fun initUI() {
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            Log.i("Marvin", "el volumen es $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())  //esto es una Coroutine o corutina
            }                                //esto es percistencia de datos
        }
        binding.swichtBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }

        binding.swichtMute.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, value)
            }
        }

        binding.swichtOscuro.setOnCheckedChangeListener { _, value ->

            if (value){
                enableDarkMode()
            }else{
                desableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }
        }
    }

    private suspend fun saveVolume(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SetingsData?> {
        return dataStore.data.map { preferences ->
            SetingsData(
                volum = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkmode = preferences[booleanPreferencesKey( KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true
            )

        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun desableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}
















