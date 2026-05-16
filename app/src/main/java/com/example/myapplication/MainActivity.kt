package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUpload = findViewById<Button>(R.id.btnUpload)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnShare = findViewById<Button>(R.id.btnShare)

        imageView = findViewById(R.id.imageView)

        // Spinner Items
        val items = arrayOf(
            "Select Item",
            "Clay Water Pot",
            "Curd Pot",
            "Clay Cooker",
            "Tea Cup",
            "Diya Lamp",
            "Flower Vase"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            items
        )

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinner.adapter = adapter

        // Upload Image
        btnUpload.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )

            startActivityForResult(intent, 1)
        }

        // Generate Benefits
        btnGenerate.setOnClickListener {

            val selected = spinner.selectedItem.toString()

            if (selected == "Select Item") {
                Toast.makeText(this, "Please select an item", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (selected) {

                "Clay Water Pot" -> """
Benefits:
• Keeps water naturally cool
• Maintains freshness
• Eco-friendly alternative
""".trimIndent()

                "Curd Pot" -> """
Benefits:
• Maintains pH balance
• Improves fermentation
• Healthy storage method
""".trimIndent()

                "Clay Cooker" -> """
Benefits:
• Retains nutrients
• Enhances taste
• Uses less oil
""".trimIndent()

                "Tea Cup" -> """
Benefits:
• Earthy aroma
• Non-toxic material
• Reusable eco cup
""".trimIndent()

                "Diya Lamp" -> """
Benefits:
• Traditional lighting
• Eco-safe product
• Supports artisans
""".trimIndent()

                "Flower Vase" -> """
Benefits:
• Handmade decoration
• Natural cooling
• Village artistry
""".trimIndent()

                else -> ""
            }

            txtResult.text = result
        }

        // Share Button
        btnShare.setOnClickListener {

            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val result = txtResult.text.toString()

            if (result.isEmpty()) {
                Toast.makeText(this, "Generate result first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create Benefit Card Bitmap
            val bitmap = Bitmap.createBitmap(
                800,
                1000,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bitmap)
            val borderPaint = Paint()
            borderPaint.color = Color.parseColor("#8B5E3C")
            borderPaint.style = Paint.Style.STROKE
            borderPaint.strokeWidth = 12f

            canvas.drawRect(10f, 10f, 790f, 990f, borderPaint)

            // Background
            canvas.drawColor(Color.parseColor("#D2A679"))
            imageUri?.let {

                val bitmapImage = MediaStore.Images.Media.getBitmap(contentResolver, it)

                val resizedBitmap = Bitmap.createScaledBitmap(
                    bitmapImage,
                    500,
                    350,
                    false
                )

                canvas.drawBitmap(resizedBitmap, 150f, 120f, null)
            }
            val paint = Paint()
            paint.color = Color.BLACK

            // Title
            paint.textSize = 65f
            paint.color = Color.parseColor("#5D4037")
            paint.isFakeBoldText = true
            canvas.drawText("Kumbara-Kala", 40f, 100f, paint)


            // Benefits
            paint.textSize = 40f

            val lines = result.split("\n")

            var yPos = 580f

            for (line in lines) {
                canvas.drawText(line, 40f, yPos, paint)
                yPos += 50f
            }

            // Artisan Info
            paint.textSize = 32f
            canvas.drawText("Made by: $name", 40f, 820f, paint)
            canvas.drawText("Phone: $phone", 40f, 880f, paint)

            // Footer
            paint.textSize = 28f
            paint.textSize = 26f
            canvas.drawText("Eco-Friendly • Handmade • Village Craft", 40f, 960f, paint)

            // Save Image
            val file = File(cacheDir, "benefit_card.png")

            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()

            val uri = androidx.core.content.FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                file
            )

            // Share Intent
            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                startActivity(Intent.createChooser(intent, "Share via"))
            } catch (e: Exception) {
                Toast.makeText(this, "No sharing app found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Image Result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {

            imageUri = data.data
            imageView.setImageURI(imageUri)
        }
    }
}