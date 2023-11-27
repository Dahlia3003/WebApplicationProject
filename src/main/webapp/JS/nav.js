function toggleIconSearch()
{
    var iconSearch = document.getElementById('iconSearch');
    iconSearchRect = iconSearch.getBoundingClientRect();
    iconSearch.style.marginRight = `0`;
    iconSearch.style.visibility = `hidden`;
    iconSearch.style.opacity = `0`;

    setSearchContainerWidth();
    var searchWrapper = document.querySelector('.search_container');
    searchWrapperRect = searchWrapper.getBoundingClientRect();
    searchWrapper.style.height = `10rem`;


    var cover = document.querySelector('.nav_cover')
    cover.style.display = "flex";
    coverRect = searchWrapper.getBoundingClientRect();
    cover.style.height = `100rem`;


    function handleTransitionEnd() {
        iconSearch.style.visibility = `visible`;
        iconSearch.style.opacity = `1`;
        iconSearch.removeEventListener('transitionend', handleTransitionEnd);
    }

    cover.addEventListener('mouseenter', function () {
        iconSearch.style.display = "block";
        searchWrapper.style.height = "";
        cover.style.height = `0`;
        cover.style.transition = "height 0.25s";
        iconSearch.style.marginRight = `2.8125rem`;
        iconSearch.addEventListener('transitionend', handleTransitionEnd);
    });

}

function setSearchContainerWidth()
{
    const wrapperDiv = document.querySelector('.width_wrapper_div');
    const wrapperRect = wrapperDiv.getBoundingClientRect();
    console.log('Wrapper Width:', wrapperRect.width);
    document.querySelector('.search_wrapper').style.width = `${wrapperRect.width -45 }px`;
}


setSearchContainerWidth();
window.addEventListener('resize', setSearchContainerWidth);