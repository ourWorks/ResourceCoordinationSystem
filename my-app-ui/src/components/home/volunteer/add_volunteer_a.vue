<template>
  <div>
    <el-page-header @back="goBack" content="步骤1 选取您的常住地址"></el-page-header>
    <div id="container">
    </div>
  </div>
</template>

<script>

export default {
  name: "add_volunteer_a",
  data() {
    return {
      preGet: true,
      longitude: 23.20211561621817,//经度
      latitude: 113.38094520264053//纬度
    }
  },
  mounted: function () {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://map.qq.com/api/gljs?v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&callback=getPosition";
    document.body.appendChild(script);
    //调用已经被mounted的方法
    // this.getPosition();
    this.$alert('你好，请等待地图加载完毕，然后单击标记您的常住位置？', {
      confirmButtonText: '确定',
      callback: action => {
        this.getPosition();
      }
    });

  },
  methods: {
    goBack() {
      this.$router.push({path: '/home'});
    },
    getPosition() {
      {
        //消除之前的填写按钮
        this.preGet = false;
        var center = new TMap.LatLng(23.20211561621817, 113.38094520264053);//设置中心点坐标
        //初始化地图
        var map = new TMap.Map("container", {
          zoom: 17, // 设置地图缩放
          center: center
        });

        //初始化marker图层
        var markerLayer = new TMap.MultiMarker({
          id: 'marker-layer',
          map: map,
          geometries: [],
        });
        var longitude;
        var latitude;
        //监听点击事件添加marker
        map.on("click", (evt) => {
            markerLayer.add({
              position: evt.latLng,
            });
            this.longitude = evt.latLng.getLat();
            this.latitude = evt.latLng.getLng();

            //进行弹框弹出进行确认
            var str = "经度:" + this.longitude + "," + "纬度:" + this.latitude + ';'
            this.$confirm(str, '确定您的位置信息？', {
              confirmButtonText: '确定',
              cancelButtonText: '重选',
            })
              //确认的调用
              .then(() => {
                this.$message({
                  type: 'success',
                  message: '成功选取位置'
                });
                //确认位置信息后
                console.log("test");
                localStorage.setItem("longitude", this.longitude);
                localStorage.setItem("latitude", this.latitude);
                this.$router.push({name:"add_volunteer_b",params:{prePath:this.$route.path,destinationPath:this.$route.params.destinationPath}});
              })
              //取消的调用
              .catch(() => {
                //腾讯地图：删除点标记
                markerLayer.setGeometries([])
              });

          }
        )
      }
    },
  }
}
</script>

<style scoped>

#container {
  high: 100%;
}

</style>
