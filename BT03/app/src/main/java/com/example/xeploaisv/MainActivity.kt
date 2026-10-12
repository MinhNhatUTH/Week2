package com.example.xeploaisv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeploaisv.ui.theme.XeploaisvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XeploaisvTheme {
                XepLoaiScreen()
            }
        }
    }
}

fun xepLoaiHocLuc(diem: Double): String {
    if (diem < 0 || diem > 10) {
        return "Không hợp lệ"
    } else if (diem >= 8.0) {
        return "Giỏi"
    } else if (diem >= 6.5) {
        return "Khá"
    } else if (diem >= 5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}

fun chonMauChu(xepLoai: String): Color {
    if (xepLoai == "Giỏi") {
        return Color.Black
    } else if (xepLoai == "Khá") {
        return Color.White
    } else if (xepLoai == "Trung bình") {
        return Color.Black
    } else if (xepLoai == "Yếu") {
        return Color.White
    } else {
        return Color.White
    }
}

fun chonMauNen(xepLoai: String): Color {
    if (xepLoai == "Giỏi") {
        return Color.Green
    } else if (xepLoai == "Khá") {
        return Color.Blue
    } else if (xepLoai == "Trung bình") {
        return Color.Yellow
    } else if (xepLoai == "Yếu") {
        return Color.Red
    } else {
        return Color.Gray
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XepLoaiScreen() {
    val diemTrungBinh = 8.5
    val xepLoai = xepLoaiHocLuc(diemTrungBinh)
    val danhSachDiem = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            TopAppBar(
                title = { Text("Xếp loại học lực", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                )
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Điểm trung bình", color = Color.DarkGray)
                    Text(
                        diemTrungBinh.toString(),
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(chonMauNen(xepLoai))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            xepLoai,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = chonMauChu(xepLoai)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("KIỂM TRA THÊM", fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                for (diem in danhSachDiem) {
                    val loai = xepLoaiHocLuc(diem)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Điểm " + diem.toString().removeSuffix(".0"),
                            color = Color.Black
                        )
                        Box(
                            modifier = Modifier
                                .background(chonMauNen(loai))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                loai,
                                fontWeight = FontWeight.Bold,
                                color = chonMauChu(loai)
                            )
                        }
                    }
                }
            }
        }
    }
}