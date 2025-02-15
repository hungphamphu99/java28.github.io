document.addEventListener("DOMContentLoaded", function () {
    let paragraph = document.querySelector("p");
    let text = paragraph.innerHTML;

    // Highlight words with length >= 5
    let highlightedText = text.replace(/\b\w{5,}\b/g, "<span style='background:yellow'>$&</span>");
    
    // Replace punctuation
    highlightedText = highlightedText.replace(/,/g, "🤔").replace(/\./g, "😲");
    
    // Update paragraph content
    paragraph.innerHTML = highlightedText;
    
    // Count words
    let wordCount = text.split(/\s+/).length;
    let wordCountDiv = document.createElement("div");
    wordCountDiv.textContent = `Số từ trong đoạn văn: ${wordCount}`;
    document.body.appendChild(wordCountDiv);
    
    // Add Facebook link
    let fbLink = document.createElement("a");
    fbLink.href = "https://www.facebook.com";
    fbLink.textContent = "facebook";
    fbLink.style.marginLeft = "10px";
    paragraph.insertAdjacentElement("afterend", fbLink);
});
