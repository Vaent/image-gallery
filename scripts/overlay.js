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

function handleUriFragment() {
    if (location.hash) {
        let frag = location.hash.substring(1);
        let img = document.querySelector(`img[src*="${frag}." i], img[src$="${frag}" i]`);
        if (img) {
            expandImage(img);
            return;
        } else {
            location.hash = "";
        }
    }
    collapseImage();
}

addEventListener('hashchange', handleUriFragment);

// check and action any initially supplied fragment
handleUriFragment();
