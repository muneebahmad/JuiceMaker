package com.muneebahmad.microsun.hndl;

/**
 * @author muneebahmad
 *
 */
import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.sound.AudioManager;

/**
 *
 * @author muneebahmad
 */
public class SoundsHandler {

    /**
     *
     */
    public static SoundsHandler _INSTANCE;

    /**
     *
     */
    public SoundsHandler() {
        //preLoadSoundsEffects();
    }

    /**
     *
     */
    public void preLoadSoundsEffects() {
        AudioManager.preloadEffect(R.raw.tech_house, AudioManager.FORMAT_MP3);
        /**
         * AudioManager.preloadEffect(R.raw.eat_munch, AudioManager.FORMAT_WAV);
                *
         */
        AudioManager.preloadEffect(R.raw.click, AudioManager.FORMAT_WAV);
        AudioManager.preloadEffect(R.raw.blending, AudioManager.FORMAT_WAV);
        AudioManager.preloadEffect(R.raw.drink, AudioManager.FORMAT_WAV);
        AudioManager.preloadEffect(R.raw.milk_pouring, AudioManager.FORMAT_WAV);
        AudioManager.preloadEffect(R.raw.rate, AudioManager.FORMAT_OGG);
        /**
         * AudioManager.preloadEffect(R.raw.milkshake_pour_effect,
         * AudioManager.FORMAT_WAV); AudioManager.preloadEffect(R.raw.eat_munch,
         * AudioManager.FORMAT_WAV); AudioManager.preloadEffect(R.raw.sip,
         * AudioManager.FORMAT_WAV);
         * AudioManager.preloadEffect(R.raw.egg_breaking_sound,
         * AudioManager.FORMAT_WAV);
                *
         */
    }

    /**
     *
     */
    public void playBackgroundFinalSound() {
        AudioManager.stopBackgroundMusic();
        AudioManager.playBackgroundMusic(R.raw.tech_house);
    }

    /**
     *
     */
    public void playButtonClickSound() {
        AudioManager.playEffect(R.raw.click);
    }

    /**
     *
     */
    public void playDrinkSound() {
        AudioManager.playEffect(R.raw.drink);
    }

    /**
     *
     */
    public void playMixingSound() {
        AudioManager.playEffect(R.raw.blending);
    }

    /**
     *
     */
    public void playPouringSound() {
        AudioManager.playEffect(R.raw.milk_pouring);
    }
    
    /**
     * 
     */
    public void playSelectedMusic() {
        AudioManager.playEffect(R.raw.rate);
    }
    /*
     public void playEatMunchSound() {
     AudioManager.playEffect(R.raw.eat_munch);
     }
	
     public void playEggBreakSound() {
     AudioManager.playEffect(R.raw.egg_breaking_sound);
     }
	
     public void playBoilingSound() {
     //TODO~
     }
	
     public void playPouringSound() {
     AudioManager.playEffect(R.raw.milkshake_pour_effect);
     }
	
     public void palySipSound() {
     AudioManager.playEffect(R.raw.sip);
     }
        
     public void playEatSound() {
     AudioManager.playEffect(R.raw.eat_munch);
     }
     * */
    /**
     * INSTANCE
     * @return
     */
    public static SoundsHandler getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new SoundsHandler();
        }
        return _INSTANCE;
    }
}//end class
