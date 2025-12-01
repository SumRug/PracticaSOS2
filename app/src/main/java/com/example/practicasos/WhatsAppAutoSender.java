package com.example.practicasos;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class WhatsAppAutoSender extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Solo actuar si es WhatsApp
        if (!"com.whatsapp".equals(event.getPackageName())) return;

        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;

        try {
            // Busca el botón de enviar (IDs pueden variar)
            List<AccessibilityNodeInfo> sendButtons = rootNode
                    .findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");


            // Simula clic en "Enviar" si se encontró el botón
            if (!sendButtons.isEmpty()) {
                sendButtons.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.d("SOS", "Mensaje enviado automáticamente");
            }
        } finally {
            rootNode.recycle(); // Liberar memoria
        }
    }

    @Override
    public void onInterrupt() {
        Log.e("SOS", "Servicio interrumpido");
    }
}