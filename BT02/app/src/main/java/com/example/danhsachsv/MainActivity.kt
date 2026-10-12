package com.example.danhsachsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.danhsachsv.ui.theme.DanhsachsvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DanhsachsvTheme {
                DanhSachSVScreen()
            }
        }
    }
}

data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanhSachSVScreen() {
    val danhSachSinhVien = listOf(
        Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị Bình", null, "0912 888 999"),
        Student("Lê Minh Chi", "chi@sv.edu.vn", null),
        Student("Phạm Quốc Dũng", null, null),
        Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
    )

    var soThieuEmail = 0
    for (sv in danhSachSinhVien) {
        if (sv.email == null) {
            soThieuEmail += 1
        }
    }

    Scaffold(
        containerColor = Color(0xFFF2F3F8),
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sinh viên", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2B4170)
                )
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {

            for (sv in danhSachSinhVien) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    Column {
                        Text(sv.name, fontWeight = FontWeight.Bold, color = Color.Black)

                        val emailHienThi = sv.email ?: "Chưa cập nhật"
                        val mauEmail = if (sv.email == null) Color.Red else Color.DarkGray
                        Row {
                            Text("Email: ", color = Color.DarkGray)
                            Text(emailHienThi, color = mauEmail)
                        }

                        val sdtHienThi = sv.phone ?: "Chưa cập nhật"
                        val mauSdt = if (sv.phone == null) Color.Red else Color.DarkGray
                        Row {
                            Text("SĐT: ", color = Color.DarkGray)
                            Text(sdtHienThi, color = mauSdt)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Tổng: ${danhSachSinhVien.size} sinh viên", color = Color.Black)
                Text("Thiếu email: $soThieuEmail", color = Color.Black)
            }
        }
    }
}