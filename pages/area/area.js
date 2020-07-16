// pages/area/area.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    areaList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;    //将当前页面对象暂存到that
    wx.request({
      url: 'http://localhost:8080/SpringBootDemo/area/list',
      method: 'GET',
      success: (res) => {
        var resList = res.data.areaList;
        if (resList == null) {
          wx.showToast({
            title: "获取数据失败：" + res.data.errMsg,
          })
        } else {
          that.setData({
            areaList: resList
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  // 删除区域信息
  deleteArea: function (e) {
    var that = this;
    wx.showModal({
      title: "提示",
      content: "是否删除" + e.target.dataset.area.areaName + "?",
      success: (modal) => {
        if (modal.confirm) {    //用户点击确定
          wx.request({
            url: 'http://localhost:8080/SpringBootDemo/area/remove',
            data: { 'areaId': e.target.dataset.area.areaId },
            method: "GET",
            success: (res) => {
              var toastText;
              if (res.data.success) {
                toastText = "删除成功";
                that.setData({
                  areaList: that.data.areaList.splice(e.target.dataset.index)
                })
              } else {
                toastText = "删除失败，" + res.data.errMsg;
              }
              wx.showToast({
                title: toastText,
                duration: 2000
              })
            }
          })
        }
      }
    })
  }
})