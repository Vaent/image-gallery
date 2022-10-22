"use strict";

/* fiveYearsAgo value is used by the toggleOlderContent function
but assigned at script level to avoid recalculation every time the switch is toggled
and avoid the edge case where an image becomes more than 5 years old while the page is open. */
const fiveYearsAgo = new Date().setFullYear(new Date().getFullYear() - 5).valueOf();

/* Generic behaviour shared by all toggle switches.
Switches must be set up with exactly one "toggle slider" and two "toggle position" elements in the HTML.
The data-toggle-position values are 0 and 1, regardless of any other labels used.
Returns true iff the toggle operation was successfully executed. */
function toggle(elementClicked) {
    if (elementClicked.classList.contains("toggle-slider") || elementClicked.classList.contains("toggle-not-selected")) {
        const siblings = Array.from(elementClicked.parentElement.children);
        const slider = siblings.find(s => s.classList.contains("toggle-slider"));
        const oldPosition = slider.dataset.currentlySelected;
        const newPosition = 1 ^ oldPosition;
        slider.dataset.currentlySelected = newPosition;

        const oldSelectedElement = siblings.find(s => s.dataset.togglePosition == oldPosition);
        const newSelectedElement = siblings.find(s => s.dataset.togglePosition == newPosition);
        oldSelectedElement.classList.replace("toggle-currently-selected", "toggle-not-selected");
        newSelectedElement.classList.replace("toggle-not-selected", "toggle-currently-selected");
        // unselected element becomes clickable, selected element ceases to be clickable
        oldSelectedElement.onclick = newSelectedElement.onclick;
        newSelectedElement.onclick = null;

        return true;
    }
}

/* Specific behaviour of the toggle switch which shows/hides images created more than 5 years ago. */
function toggleOlderContent(elementClicked) {
    if (toggle(elementClicked)) {
        // "show older content" toggle uses position 0 for "no", position 1 for "yes"
        const isSelectedShowOlderContent = elementClicked.parentElement.getElementsByClassName("toggle-slider")[0].dataset.currentlySelected;
        for (let img of document.getElementsByClassName("image-box")) {
            if (isSelectedShowOlderContent == true) {
                img.style.display = "";
            } else if (Date.parse(img.dataset.dateCreated) < fiveYearsAgo) {
                img.style.display = "none";
            }
        }
    }
}
