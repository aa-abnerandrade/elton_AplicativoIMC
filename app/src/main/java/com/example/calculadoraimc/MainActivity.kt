package com.example.calculadoraimc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity () {

    lateinit var textView_peso : TextView;
    lateinit var editTextText_peso : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);
//        setContent {
//            CalculadoraIMCTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
        Log.d("LifeCycle", "onCreate");
        textView_peso = findViewById(R.id.textView_peso);
        editTextText_peso = findViewById(R.id.editTextText_peso);
    }

    fun calcularButtonOnClick(v: View) {
        Toast.makeText(
            this,
            "Seu peso é ",
            Toast.LENGTH_LONG
        ).show();
    }

    override fun onStart() {
        super.onStart();
        Log.v("LifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("LifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.wtf("LifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("LifeCycle", "onDestroy")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraIMCTheme {
        Greeting("Bictor")
    }
}