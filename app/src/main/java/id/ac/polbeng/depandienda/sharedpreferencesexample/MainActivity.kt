package id.ac.polbeng.depandienda.sharedpreferencesexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.ac.polbeng.depandienda.sharedpreferencesexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Membuat file SharedPreferences kustom dengan nama package + "-TESTFILE"
        val filename = "$packageName-TESTFILE"
        val pref = getSharedPreferences(filename, Context.MODE_PRIVATE)

        // Tombol Simpan Data
        binding.btnSave.setOnClickListener {
            val editor = pref.edit()
            editor.putString("firstName", binding.etFirstName.text.toString())
            editor.putString("lastName", binding.etLastName.text.toString())
            editor.apply()
            Toast.makeText(this, "Saved Data!", Toast.LENGTH_LONG).show()
        }

        // Tombol Muat Data
        binding.btnLoad.setOnClickListener {
            val firstName = pref.getString("firstName", "")
            val lastName = pref.getString("lastName", "")
            val output = "$firstName $lastName"
            binding.etFirstName.setText(firstName)
            binding.etLastName.setText(lastName)
            binding.tvOutput.text = output
        }

        // Tombol Pindah ke SecondActivity
        binding.btnSecondActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etFirstName.setText("")
        binding.etLastName.setText("")
        binding.tvOutput.text = ""
    }
}