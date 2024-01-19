// ==UserScript==
// @name         下载bing背景图片2
// @namespace    http://tampermonkey.net/
// @version      2024-01-17
// @description  try to take over the world!
// @author       gpengtao
// @match        https://cn.bing.com/
// @icon         https://www.google.com/s2/favicons?sz=64&domain=https://cn.bing.com/
// @grant        GM_download
// @grant        unsafeWindow
// ==/UserScript==

(function () {
    'use strict';

    // 日期
    let d = new Date();
    let date = d.getFullYear() + ('0' + (d.getMonth() + 1)).slice(-2) + ('0' + d.getDate()).slice(-2);

    // 是否已经保存过了
    let key = 'save_bing_img';
    let value = localStorage.getItem(key);
    if (value === date) {
        console.log('已经保存过 ' + value)
        return;
    }

    // 找到img_cont元素的背景图片，即首页的背景图片
    let backgroundImage = document.getElementsByClassName('img_cont')[0].style.backgroundImage;
    // 解析出来里面的url
    let url = backgroundImage
        .replaceAll('"', '')
        .replaceAll('url(', '')
        .replaceAll(')', '')
    // 替换为uhd超高清图片地址
    let download_url = url.split('_1920x1080')[0] + '_UHD.jpg'

    // 下载图片的文件名字
    let img_file_name = 'bing-' + date + '-' + download_url.split('id=')[1];

    // 下载图片
    GM_download({
        url: download_url,
        name: img_file_name,
        saveAs: true
    });

    // 记录已下载过
    localStorage.setItem(key, date)
})();