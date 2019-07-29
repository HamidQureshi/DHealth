package com.example.hamid.dhealth.ui.Activities

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button

import com.example.hamid.dhealth.R
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.shockwave.pdfium.PdfDocument

class PDFViewActivity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener {

    lateinit var pdfView: PDFView
    internal var pageNumber: Int? = 0
    internal var pdfFileName: String? = null
    internal var TAG = "PdfActivity"
    internal var position = -1
    internal var URI: Uri? = null
    lateinit var btn_attach: Button
    internal var show: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)
        btn_attach = findViewById(R.id.btn_attach)
        val bundle = intent.extras
        if (bundle != null) {
            URI = Uri.parse(bundle.getString("URI"))
            pdfFileName = bundle.getString("filename")
            show = bundle.getBoolean("showAttachButton")

            if (show!!) {
                btn_attach.visibility = View.VISIBLE
            }
        }
        init()
    }

    private fun init() {
        pdfView = findViewById<View>(R.id.pdfView) as PDFView
        position = intent.getIntExtra("position", -1)
        displayFromSdcard()
    }

    private fun displayFromSdcard() {

        pdfView.fromUri(URI)
                .defaultPage(pageNumber!!)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        title = String.format("%s %s / %s", pdfFileName, page + 1, pageCount)
    }

    override fun loadComplete(nbPages: Int) {
        val meta = pdfView.documentMeta
        printBookmarksTree(pdfView.tableOfContents, "-")

    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.title, b.pageIdx))

            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }

    fun attachReport(view: View) {
        finish()
    }
}
