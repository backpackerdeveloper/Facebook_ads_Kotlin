package com.shubhamtripz.facebookads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.facebook.ads.*

class MainActivity : AppCompatActivity() {

    private lateinit var adView: com.facebook.ads.AdView
    var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AudienceNetworkAds.initialize(this)

        // banner ad
        adView = com.facebook.ads.AdView(
            this,
            "Banner_placement_id",
            com.facebook.ads.AdSize.BANNER_HEIGHT_50
        )
        val adContainer = findViewById<LinearLayout>(R.id.banner_container)
        adContainer.addView(adView)
        adView.loadAd()
        // banner end

        // interstitial ad

        interstitialAd = InterstitialAd(this, "Interstitial_Placement_ID")
        val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {

            }

            override fun onAdLoaded(p0: Ad?) {
                showInterstitial()
            }

            override fun onAdClicked(p0: Ad?) {

            }

            override fun onLoggingImpression(p0: Ad?) {

            }

            override fun onInterstitialDisplayed(p0: Ad?) {

            }

            override fun onInterstitialDismissed(p0: Ad?) {

            }


        }

        interstitialAd!!.loadAd(
            interstitialAd!!.buildLoadAdConfig()
                .withAdListener(interstitialAdListener)
                .build()
        )

    }

    private fun showInterstitial(){
        if (interstitialAd!!.isAdLoaded){
            interstitialAd!!.show()
        }
        else{
            interstitialAd!!.loadAd()
        }
    }

    override fun onDestroy() {
        if (interstitialAd!! != null){
            interstitialAd!!.show()
        }
        super.onDestroy()
    }

}