package model;

import java.util.List;

public class Example {
    private boolean canBeRotated;
    private boolean searchVoiceClickSoundEnabled;
    private List<Items> items;

    public boolean isCanBeRotated() {
        return canBeRotated;
    }

    public void setCanBeRotated(boolean canBeRotated) {
        this.canBeRotated = canBeRotated;
    }

    public boolean isSearchVoiceClickSoundEnabled() {
        return searchVoiceClickSoundEnabled;
    }

    public void setSearchVoiceClickSoundEnabled(boolean searchVoiceClickSoundEnabled) {
        this.searchVoiceClickSoundEnabled = searchVoiceClickSoundEnabled;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
