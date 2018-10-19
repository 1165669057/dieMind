package com.example.administrator.testapp.application;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2018/10/14.
 *
 * @GlideExtension 注解用于标识一个扩展GlideApi的类。任何扩展
 * Glide Api 的类都必须使用这个注解来标记，否则其中注解的方法就会被忽略
 *
 * 1、@GlideExtension 注解的类  应当有工具类的思维 这种类应该有一个私有的
 *   空的构造方法 应为final 类型 包含静态方法 可以有静态变量 可以引用其他类和对象
 *   当 AppGlideModule 被发现时，所有有效的 Glide 扩展类 会被合并，所有的选项在 API 中均可以被调用。
 *   合并冲突会导致 Glide 的 Annotation Processor 抛出编译错误。
 *   被 @GlideExtention 注解的类有两种扩展方式：
 *   1、GlideOption - 为 RequestOptions 添加一个自定义的选项。
 *   2、GlideType - 添加对新的资源类型的支持(GIF，SVG 等等)。
 *
 *
 *
 */
@GlideExtension
public class MyAppExtension {
    private static final  int MINI_THUMB_SIZE=100;
    private static final RequestOptions DECODE_TYPE_GIF=RequestOptions.decodeTypeOf(GifDrawable.class).lock();
    private MyAppExtension(){}
    /**
     *
     * @param options
     * 用@GlideOption注解的静态方法用于扩展RequestOptions
     * GlideOption可以：
     *  1.定义一个在Application模块中频繁使用的选项集合
     *  2.创建新的选项 通常与Glide的Option类一起使用
     *
     */
    @GlideOption
    public static void miniThumbSize(RequestOptions options,int size){
        options.fitCenter().override(size);
    }

    /**
     *
     * @param requestBuilder
     *用@GlideType注解的静态方法用于扩展RequestManager
     * 被@GlideType注解的方法允许你添加对新的资源类型的支持,包括指定默认选项
     * 被 @GlideType 标记的方法必须使用 RequestBuilder<T> 作为其第一个参数，
     * 这里的泛型 <T> 对应 @GlideType 注解中传入的类。该方法应为静态方法，且返回值为空。
     * 方法必须定义在一个被 @GlideExtension 注解标记的类中。
     *eg: 添加对 GIF 的支持
     */
    @GlideType(GifDrawable.class)
    public static void setGif(RequestBuilder<GifDrawable> requestBuilder){
        requestBuilder.transition(new DrawableTransitionOptions())
                .apply(DECODE_TYPE_GIF);
    }











}
