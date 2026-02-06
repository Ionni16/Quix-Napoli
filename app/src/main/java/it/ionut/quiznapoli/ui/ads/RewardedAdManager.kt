package it.ionut.quiznapoli.ui.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAdManager(
    private val context: Context,
    private val adUnitId: String
) {
    private var rewardedAd: RewardedAd? = null
    private var loading = false

    fun preload() {
        if (loading || rewardedAd != null) return
        loading = true

        RewardedAd.load(
            context,
            adUnitId,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    loading = false
                    rewardedAd = ad
                    rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            rewardedAd = null
                            preload()
                        }

                        override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                            rewardedAd = null
                            preload()
                        }
                    }
                }

                override fun onAdFailedToLoad(p0: com.google.android.gms.ads.LoadAdError) {
                    loading = false
                    rewardedAd = null
                }
            }
        )
    }

    fun show(activity: Activity, onReward: () -> Unit, onNoAd: () -> Unit = {}) {
        val ad = rewardedAd
        if (ad == null) {
            onNoAd()
            preload()
            return
        }

        ad.show(activity) { _: RewardItem ->
            onReward()
        }
    }
}
