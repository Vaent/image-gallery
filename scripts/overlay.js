"use strict";

const expandedImageOverlay = document.getElementById("expanded-image-overlay");
const expandedImage = document.getElementById("expanded-image");

function expandImage(thumb) {
    expandedImage.src = thumb.src;
    expandedImageOverlay.style.display = "block";
}

function collapseImage() {
    expandedImageOverlay.style.display = "none";
}
