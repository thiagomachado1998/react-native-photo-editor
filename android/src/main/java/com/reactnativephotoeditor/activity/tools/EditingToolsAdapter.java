package com.reactnativephotoeditor.activity.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Locale;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reactnativephotoeditor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @version 0.1.2
 * @since 5/23/2018
 */
public class EditingToolsAdapter extends RecyclerView.Adapter<EditingToolsAdapter.ViewHolder> {

  private List<ToolModel> mToolList = new ArrayList<>();
  private OnItemSelected mOnItemSelected;

  public EditingToolsAdapter(OnItemSelected onItemSelected) {
    mOnItemSelected = onItemSelected;
    mToolList.add(new ToolModel(getTranslatedSignature(), R.drawable.ic_sticker, ToolType.STICKER));
    mToolList.add(new  (getTranslatedText(), R.drawable.ic_smallcaps, ToolType.TEXT));
  }

  public interface OnItemSelected {
    void onToolSelected(ToolType toolType);
  }

  // Método para obter a tradução com base no idioma do dispositivo
  private String getTranslatedSignature() {
    String languageCode = Locale.getDefault().getLanguage();

    switch (languageCode) {
        case "pt": 
            return "Assinaturas";
        case "es":
            return "Firmas"; 
        case "fr": 
            return "Signatures"; 
        case "de": 
            return "Unterschriften";  
        case "ja": 
            return "署名（複数）";  
        case "zh": 
            return "签名（复数）";  
        default: 
            return "Signatures"; 
    }
  }

  private String getTranslatedText() {
    String languageCode = Locale.getDefault().getLanguage();

    switch (languageCode) {
        case "pt": 
            return "Texto";
        case "es":
            return "Texto"; 
        case "fr": 
            return "Texte"; 
        case "de": 
            return "Text";  
        case "ja": 
            return "テキスト";  
        case "zh": 
            return "文本";  
        default: 
            return "Text"; 
    }
}


  class ToolModel {
    private String mToolName;
    private int mToolIcon;
    private ToolType mToolType;

    ToolModel(String toolName, int toolIcon, ToolType toolType) {
      mToolName = toolName;
      mToolIcon = toolIcon;
      mToolType = toolType;
    }

  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.row_editing_tools, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ToolModel item = mToolList.get(position);
    holder.txtTool.setText(item.mToolName);
    holder.imgToolIcon.setImageResource(item.mToolIcon);
  }

  @Override
  public int getItemCount() {
    return mToolList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imgToolIcon;
    TextView txtTool;

    ViewHolder(View itemView) {
      super(itemView);
      imgToolIcon = itemView.findViewById(R.id.imgToolIcon);
      txtTool = itemView.findViewById(R.id.txtTool);
      itemView.setOnClickListener(v -> mOnItemSelected.onToolSelected(mToolList.get(getLayoutPosition()).mToolType));
    }
  }
}

