window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    let pagenum = urlParams.get('page');
    if (pagenum == null)
    {
        pagenum = 0;
    }
    console.log(pagenum);
    showPage(pagenum);
};


function showPage(pageId) {
    currentPage = pageId
    const pages = document.querySelectorAll('.list_product');
    pages.forEach(page => {
        page.style.display = 'none';
    });


    const selectedPage = document.getElementById(pageId);
    if (selectedPage) {
        selectedPage.style.display = 'flex';
    }
}s
