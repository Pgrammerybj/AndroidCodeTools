![](http://orsggluk8.bkt.clouddn.com/image/github/2017-08-02-%E6%A0%87%E9%A2%98%E5%A4%B4.jpg)<br>
# AndroidCodeTools (持续更新...)

> 写这个Tools前我就一直在想，我们自己的项目中有很多很棒的工具类和一些自定义的View，而这些View或者工具类在网上或有或没，既然他们懒得弄或者不屑弄，那好吧我来整理吧。也就是基于这个原因我开始创建并且维护这个库，我所希望的是这个库能帮助到一部分Android初级的开发人员，也希望每一个看到 AndroidCodeTools 
这个库的码神们，能多多的 Pull 你们开发中所遇到的优秀的工具类或者自定义View。

## 分类
* Android工具类
* Android自定义控件
* Android自定义动画

## Android工具类
1.之前在网上看到很多人遇到过在一个TextView是实现类似于Html中的超文本点击跳转的效果，正好这次项目中正好也有这个需求，然后自己也就利用SpannableStringBuilder撸了一个工具类。当然网上也有跟多不错的实现方式，我就不在这儿累赘了，大家一起交流咯。<br>

> setTextViewHyperlink(TextView textView,String content,int textColor,int start1,int end1, OnTextViewhyperlinkOnClickListener listener))

  * @param textView 文本控件
  * @param content  内容
  * @param textColor需要响应点击事件文本的颜色
  * @param start1   可点击文本1的开始角标
  * @param end1     可点击文本1的结束角标
  * @param start2   可点击文本2的开始角标（可选）
  * @param end2     可点击文本2的结束角标（可选）
  * @param listener 超链接点击监听

```js
      TextView hyperTextOne = (TextView) findViewById(R.id.tv_user_protocol_1);
            //一个文本链接
            UIUtilsView.setTextViewHyperlink(hyperTextOne,
                    getString(R.string.information_002),
                    getResources().getColor(R.color.defaultLinkText), 9, 17,
                    new UIUtilsView.OnTextViewhyperlinkOnClickListener() {
                        @Override
                        public void onClick(String text) {
                         // FIXME: 2017/8/2 这里能获得到你点击事件，后续逻辑如何自己看需求哈
                         // text :这个text就是你所点击的文字
                        }
                    }
            );
```
⬆️上面是TextView文本中有一个点击事件的调用方式<br><br>
```
 TextView hyperTextTwo = (TextView) findViewById(R.id.tv_user_protocol_2);
        //两个文本链接
        UIUtilsView.setTextViewHyperlink(hyperTextTwo,
                getString(R.string.information_001),
                getResources().getColor(R.color.defaultLinkText), 7, 15, 16, 28,
                new UIUtilsView.OnTextViewhyperlinkOnClickListener() {
                    @Override
                    public void onClick(String text) {
                        // FIXME: 2017/8/2 有人说不对啊我这儿有两个点击事件啊，
                        // 对的两个点击事件都走这一个监听，那么如何区分呢，就靠您的 text 字符串equles咯

                    }
                }
        );
```
⬆️上面是TextView文本中有多个点击事件的调用方式<br><br>
（怎么效果图没有动画？哈哈不要捉急，稍微等一下咯）<br>
![](http://orsggluk8.bkt.clouddn.com/image/github/2017-08-02-%E8%B6%85%E6%96%87%E6%9C%AC%E7%82%B9%E5%87%BB.gif)<br><br>
总结一下吧：目前是获取相应需要有点击事件和变色的文本的角标来实现的，还是有些许麻烦，望广大朋友给点儿优化意见和建议。
<br><br><br>
## Android自定义控件

1. 自定义密码、验证码输入框，类似于支付、微信支付的密码输入界面

```js
 VerifyCodeView.setInputFinishListener(new VerifyCodeView.InputFinishListener() {
            @Override
            public void inputFinish(String strCode) {
                  //密码输入完成，做你想做的
            }
        });
```
![自定义验证码输入框](http://orsggluk8.bkt.clouddn.com/image/github/2017-07-03-%E9%AA%8C%E8%AF%81%E7%A0%81%E8%BE%93%E5%85%A5%E6%A1%86.gif)

## Android自定义动画

## Licenses
```
 Copyright 2017 JackYang(杨保疆)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```