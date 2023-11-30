package com.example.amityredditsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.api.core.endpoint.AmityEndpoint
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AmityCoreClient
            .setup("", AmityEndpoint.US)
            .subscribe()

        println("AMITY STATUS :: " + AmityCoreClient.getCurrentSessionState())

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val userIdState = remember { mutableStateOf(TextFieldValue("academy9989")) }
                val displayNameState = remember { mutableStateOf(TextFieldValue("Academy IT 9989")) }

                TextField(
                    value = userIdState.value,
                    onValueChange = { userIdState.value = it },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .background(Color.White)
                        .height(64.dp),
                    label = { Text("Enter user id") }
                )

                TextField(
                    value = displayNameState.value,
                    onValueChange = { displayNameState.value = it },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .background(Color.White)
                        .height(64.dp),
                    label = { Text("Enter Display Name") }
                )

                Button(
                    onClick = {
                        val userId = userIdState.value.text
                        val displayName = displayNameState.value.text
                        lifecycleScope.launch {
                            viewModel.login(userId, displayName)
                            navigateToHomePage()
                        }
                    },
                    modifier = Modifier.padding(vertical = 48.dp, horizontal = 32.dp)
                ) {
                    Text("Login")
                }
            }
        }
    }
    private fun navigateToHomePage() {
        println("NAVIGATE TO HOME HERE")
        println(" BEFORE AMITY STATUS :: " + AmityCoreClient.getCurrentSessionState())
        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
        finish()
    }

}