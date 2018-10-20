package com.example.administrator.testapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
/**
 * Created by Administrator on 2018/10/20.
 * glide加载图片工具类
 * 注意这里涉及到glide 加载图片的一些基础用法
 *  要点 RequestOptions 类和 apply方法 应用到程序上面去
 *  requestOptions 可以实现
 *     1.占位符 Placeholders
 *     2.转换 Transformations  （用于决定你的加载完成时会发生什么）
 *            TransitionOption 可以应用以下变换：
 *               （ View淡入）
 *              （与占位符交叉淡入）
 *              （或者什么都不发生）
 *     3.缓存策略 Caching Strategies
 *     4.组件特有的设置选项 编码质量 Bitmap的解码配置
 *
 * RequestBuilder （它是glide请求的骨架 负责携带请求的url和你的设置项来开始一个新的加载过程）
 *   使用RequestBuilder可以指定：
 *      加载的资源类型 bitmap drawable或其他
 *      加载的资源地址（url/model）
 *      最终加载到的View
 *      任何你想应用的一个或多个RequestOption对象
 *      任何你想应用的一个或多个 TransitionOption对像
 *      任何你想加载的略缩图thumbnail()
 *      要构造一个RequestBuilder对象
 *         RequestBuilder <Drawable> requestBuilder=Glide.with(fragment).asDrawable()
 *         或者先调用Glide.with() 然后 load();
 *         RequestBuilder<Drawable> requestBuilder = Glide.with(fragment).load(url);
 *   选择资源类型
 *     RequestBuilders 默认情况下会得到一个Drawable RequestBuilder 但你可以使用 as...系列来改变请求类型
 *       下面代码会让你得到一个Bitmap 类型
 *      eg:  RequestBuilder<Bitmap> requestBuilder = Glide.with(fragment).asBitmap();
 *   应用RequestOptions
 *   上面提到可以使用apply()方法应用 RequestOptions 使用 transition()方法应用 TransitionOptions
 *   RequestBuilder <Drawable> requestBuilder=Glide.with(fragment).asDrawable();
 *     requestBuilder.apply(requestOptions);
       requestBuilder.transition(transitionOptions);
    RequestBuilder 也可以被复用于开始多个请求
         RequestBuilder<Drawable> requestBuilder =
             Glide.with(fragment)
             .asDrawable()
             .apply(requestOptions);

         for (int i = 0; i < numViews; i++) {
               ImageView view = viewGroup.getChildAt(i);
               String url = urls.get(i);
               requestBuilder.load(url).into(view);
         }
 缩略图 (Thumbnail) 请求
    Glide的 thumbnail()API 可让你指定一个RequestBuilder 以与你的主请求并行启动
       thumbnail()会在主请求加载过程中展示 如果主请求在略缩图请求之前完成 略缩图请求中的图像
       将不会被展示 thumbnail()API 允许你简单快速地加载图像的低分辨率版本  并且同时加载图像的无损版本
        这可以减少用户盯着加载指示器【列如进度条】
    thumbnail()API 对本地和远程图片都适用 尤其是当低分辨率缩略图存于Glide的磁盘缓存时它们将很快被加载出来
    Glide.with(fragment).load(url).thumbnail(Glide.with(fragment).load(thumbnailurl)).into(imageView)
  只要你的 thumbnailUrl 指向的图片比你的主 url 的分辨率更低，它将会很好地工作。相当数量的加载 API 提供了不同的指定图片尺寸的方法，
   它们尤其适用于 thumbnail() API。

 如果你仅仅想加载一个本地图像，或者你只有一个单独的远程 URL， 你仍然可以从缩略图 API 受益。
 请使用 Glide 的 override 或 sizeMultiplier API 来强制 Glide 在缩略图请求中加载一个低分辨率图像：
     int thumbnailSize = ...;
     Glide.with(fragment)
      .load(localUri)
      .thumbnail(Glide.with(fragment)
      .load(localUri)
      .override(thumbnailSize))
      .into(view);
 thumbnail() 方法有一个简化版本，它只需要一个 sizeMultiplier 参数。
  如果你只是想为你的加载相同的图片，但尺寸为 View 或 Target 的某个百分比的话特别有用：
      Glide.with(fragment)
        .load(localUri)
        .thumbnail(0.25f)
        .into(imageView);

 在失败时开始新的请求
    从Glide 4.3开始  可以用 error API来指定一个RequestBuilder 以在请求失败时开始一次新的加载
      列如 在请求primaryUrl失败后加载fallbackUrl
     Glide.with(fragment)
       .load(primaryUrl)
       .error(Glide.with(fragment)
       .load(fallbackUrl))
       .into(imageView);
    如果主请求成功完成，这个error RequestBuilder 将不会被启动。如果你同时指定了一个 thumbnail() 和一个 error() RequestBuilder，
      则这个后备的 RequestBuilder 将在主请求失败时启动，即使缩略图请求成功也是如此。


 组件选项
   Option 类是给Glide的组件添加参数的通用方法
      包括  ModelLoaders  ResourceDecoders  ResourceEncoders  Encoders
     Glide的内置组件提供了设置项，自定义的组件也可以添加设置项。
     Option 通过 RequestOptions 类应用到请求上：

 *
 */

public class GlideAppUtil {
    private static final String TAG = "GlideAppUtil";
    /**
     * 圆角图片加载
     * @author leibing
     * @createTime 2016/8/15
     * @lastModify 2016/8/15
     * @param context 上下文
     * @param imageView 图片显示控件
     * @param url 图片链接
     * @param defaultImage 默认占位图片
     * @param errorImage 加载失败后图片
     * @param radius 图片圆角半径
     * @return
     */
    public static void load(Context context, ImageView imageView, String url, int defaultImage,
                            int errorImage , int radius){
        //RequestOptions 设置请求参数，通过apply方法设置
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new CornersTranform(context, radius));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url).apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载resoures下的文件
     * @param context
     * @param imageView
     * @param url
     * @param defaultImage
     * @param errorImage
     */
    public static void loadImgId(Context context, final ImageView imageView, int url, int defaultImage,
                                 int errorImage, int radius) {
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new CornersTranform(context, radius));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载圆形头像
     * @param context
     * @param imageView
     * @param url
     * @param defaultImage
     * @param errorImage
     */
    public static void loadCircle(Context context, final ImageView imageView, String url, int defaultImage,
                                  int errorImage) {
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .dontAnimate() //防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new GlideCircleTransform(context));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }
}


