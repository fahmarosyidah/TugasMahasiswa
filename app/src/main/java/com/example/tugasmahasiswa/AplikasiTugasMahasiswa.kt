package com.example.tugasmahasiswa

import android.app.Application
import com.example.tugasmahasiswa.repository.ContainerApp
import com.example.tugasmahasiswa.repository.ContainerDataApp

class AplikasiTugasMahasiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}