package com.example.legionaryapp.components.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.R
import com.example.legionaryapp.components.tasks.DeadlineTasks
import com.example.legionaryapp.components.tasks.SectionHeader
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.data.filterByCategory
import kotlinx.coroutines.runBlocking
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber


@Composable
fun GuideBody() {
    Column(modifier = Modifier.fillMaxSize()) {
        SectionHeader(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
            progress = 0f,
            showingProgress = false,
            title = listOf("Информация"),
            subtitle = "Здесь находится вся информация, необходимая в процессе адаптации и знакомства с e-Legion",
            titleTopPadding = 40.dp,
            imageId = R.drawable.circle,
            imageModifier = Modifier
                .size(5.dp)
//                .offset(x = (50).dp, y = (-50).dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        InfoCard(modifier = Modifier
            .weight(3f)
            .fillMaxWidth()
            .height(170.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(25.dp))
    }
}

@Composable
fun InfoCard(modifier: Modifier) {

    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = "Гайд для новых сотрудников", fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.pdf_icon),
                contentDescription = null,
                modifier = Modifier
                    .weight(3f)
                    .size(70.dp)
                    .clip(CircleShape),
            )
            Column(modifier = Modifier.weight(7f), verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Познавай наш мир!",
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Row {
                    Button(
                        onClick = {
                            runBlocking {
                                val manager =
                                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                                val uri: Uri =
                                    Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
                                val request = DownloadManager.Request(uri)
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                                manager?.let {
                                    val reference: Long = it.enqueue(request)
                                }
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                    ) {
                        Text("Скачать")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedButton(
                        onClick = {
                            runBlocking {
                                val url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
                                val i = Intent(Intent.ACTION_VIEW)
                                i.data = Uri.parse(url)
                                context.startActivity(i)
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                        Text("Читать")
                    }
                }
            }
        }
    }

}