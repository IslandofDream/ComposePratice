package com.junwoo.composepractice.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junwoo.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeWithUiState(viewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
fun ComposeWithUiState(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val count by viewModel.uiState.collectAsState()

    Column {

        RenderWithState(uiState = count)

        Button(
            onClick = { viewModel.plusCount() },
            modifier = modifier
        ) {
            Text("Plus!")
        }
    }

}

@Composable
fun RenderWithState(uiState: UiState<Int>) {
    val context = LocalContext.current

    when (uiState) {
        is UiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                color = MaterialTheme.colorScheme.secondary,
            )
        }

        is UiState.Success -> {
            Text(text = uiState.data.toString())
            Toast.makeText(
                context,
                "Count : ${uiState.data}",
                Toast.LENGTH_SHORT
            ).show()
        }

        else -> Unit

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        RenderWithState(uiState = UiState.Loading)
    }
}