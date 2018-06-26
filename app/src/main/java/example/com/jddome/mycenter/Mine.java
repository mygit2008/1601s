package example.com.jddome.mycenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import example.com.base.BaseFragment;
import example.com.base.mvp.BaseModel;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.api.BaseAPI;
import example.com.jddome.mycenter.bean.GetUserInfo;
import example.com.jddome.mycenter.login.LoginActivity;
import example.com.jddome.mycenter.model.UploadModel;
import example.com.jddome.mycenter.presenter.UploadPresenter;
import example.com.jddome.mycenter.view.IUploadView;
import example.com.jddome.mycenter.view.MapActivity;
import example.com.jddome.utils.RetrofitUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * @author zhangjunyou
 * @date 2018/6/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class Mine extends BaseFragment<UploadPresenter> implements View.OnClickListener, IUploadView {

    private View view;
    private SimpleDraweeView headPo;
    /**
     * 昵称
     */
    private TextView nickname;
    /**
     * 账号管理 >
     */
    private TextView director;
    private SharedPreferences sp;
    //本地相册图片选择
    private String[] mCustomItem = {"本地相册", "相机拍照"};
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private File Defaltefile;
    private Uri tempUri;
    private File file;
    private GetUserInfo.DataBean data;
    private TextView mMap;
    private TextView exit;

    protected void initView(View view) {
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        headPo = (SimpleDraweeView) view.findViewById(R.id.head_po);
        headPo.setOnClickListener(this);
        nickname = (TextView) view.findViewById(R.id.nickname);
        director = (TextView) view.findViewById(R.id.director);
        director.setOnClickListener(this);
        mMap = (TextView) view.findViewById(R.id.mymap);
        mMap.setOnClickListener(this);
        exit = (TextView) view.findViewById(R.id.exit);
        exit.setOnClickListener(this);
    }

    @Override
    protected BaseModel initModel() {
        return new UploadModel();
    }

    @Override
    protected UploadPresenter initPresenter() {
        return new UploadPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.mine_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (sp.getBoolean("falg", false)) {
            headPo.setImageURI(Uri.parse(sp.getString("iconur", "")));
            nickname.setText(sp.getString("screenname", ""));
            mMap.setText("地址：" + sp.getString("city", "北京市"));
        } else {
            if (sp.getString("uid", "0") != null) {
                presenter.getUserInfo(sp.getString("uid", "1"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.head_po:
                showSingleChoiceDialog();
                break;
            case R.id.exit:
                sp.edit().clear().commit();
                break;
            case R.id.mymap:
                Intent itm = new Intent(MyApp.context, MapActivity.class);
                startActivity(itm);
                break;
            case R.id.director:
                Intent it = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(it, Activity.RESULT_FIRST_USER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取相机返回值
        if (resultCode == -1) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        Bitmap parcelable = extras.getParcelable("data");
                        saveFile(parcelable, "head.jpg");
                        Log.e("图片路径", file.getName().trim());
                        final Map<String, Object> map = new HashMap<>();
                        map.put("uid", sp.getString("uid", "0"));
                        map.put("file", file != null ? file : Defaltefile);
                        RetrofitUtil.upLoadFile(BaseAPI.UPLOAD, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                presenter.getUserInfo(sp.getString("uid", "0"));
                            }
                        });
                    }
                    break;
            }
        }
    }

    public void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("选择：");
        builder.setItems(mCustomItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Defaltefile = new File(Environment.getExternalStorageDirectory() + "/revoeye/", "image.jpg");
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //判断sd卡是否存在
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

    //将图片保存到本地，参数一个是bitmap,一个是文件名字
    public void saveFile(Bitmap bm, String fileName) {
        try {
            String path = getSDPath() + "/revoeye/";
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            file = new File(path + fileName);
            BufferedOutputStream bos;
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getUserInfo(GetUserInfo getUserInfo) {
        if (getUserInfo.getData() != null) {
            data = getUserInfo.getData();
            headPo.setImageURI(Uri.parse(data.getIcon()));
            nickname.setText(data.getUsername());
        }
    }
}
