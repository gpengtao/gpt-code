// ==UserScript==
// @name         下载bing背景图片
// @namespace    http://tampermonkey.net/
// @version      2024-01-17
// @description  try to take over the world!
// @author       gpengtao
// @match        https://cn.bing.com/
// @icon         https://www.google.com/s2/favicons?sz=64&domain=https://cn.bing.com/
// @grant        none
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

    // 下载
    const xhr = new XMLHttpRequest();
    xhr.open('GET', download_url, true);
    xhr.responseType = 'blob';
    xhr.onload = () => {
        if (xhr.status === 200) {
            // 获取文件blob数据
            const export_blob = new Blob([xhr.response]);

            // 创建a对象。
            const save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
            save_link.href = window.URL.createObjectURL(export_blob);
            save_link.download = img_file_name;
            save_link.click();
        }
    };
    xhr.send();

    // 记录已下载过
    localStorage.setItem(key, date)
})();