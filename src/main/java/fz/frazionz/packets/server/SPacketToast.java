package fz.frazionz.packets.server;

import java.io.IOException;
import java.util.Locale;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;

public class SPacketToast implements Packet<INetHandlerPlayClient> {

    private Type type;

    private Icon icon;

    private ITextComponent title;

    private ITextComponent subtitle;

    public SPacketToast() {}

    public SPacketToast(Type type, Icon icon, ITextComponent title, ITextComponent subtitle) {
        this.type = type;
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.type = (Type)buf.readEnumValue(Type.class);
        this.icon = (Icon)buf.readEnumValue(Icon.class);
        this.title = buf.readTextComponent();
        this.subtitle = buf.readTextComponent();
    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(this.type);
        buf.writeEnumValue(this.icon);
        buf.writeTextComponent(this.title);
        buf.writeTextComponent(this.subtitle);
    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleToast(this);
    }

    public Type getType() {
        return this.type;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public enum Type {
        NORMAL, SUCCESS;

        public static Type byName(String name) {
            for (Type spackettoast$type : values()) {
                if (spackettoast$type.name().equalsIgnoreCase(name))
                    return spackettoast$type;
            }
            return NORMAL;
        }

        public static String[] getNames() {
            String[] astring = new String[(values()).length];
            int i = 0;
            for (Type spackettoast$type : values())
                astring[i++] = spackettoast$type.name().toLowerCase(Locale.ROOT);
            return astring;
        }
    }

    public enum Icon {
        LOGO(6, 5, 0, 0, 24, 24),
        MOBS(132, 8, 0, 64, 24, 18);

        private final int xToast;

        private final int yToast;

        private final int xTexture;

        private final int yTexture;

        private final int w;

        private final int h;

        Icon(int xToast, int yToast, int xTexture, int yTexture, int w, int h) {
            this.xToast = xToast;
            this.yToast = yToast;
            this.xTexture = xTexture;
            this.yTexture = yTexture;
            this.w = w;
            this.h = h;
        }

        public static Icon byName(String name) {
            for (Icon spackettoast$icon : values()) {
                if (spackettoast$icon.name().equalsIgnoreCase(name))
                    return spackettoast$icon;
            }
            return LOGO;
        }

        public static String[] getNames() {
            String[] astring = new String[(values()).length];
            int i = 0;
            for (Icon spackettoast$icon : values())
                astring[i++] = spackettoast$icon.name().toLowerCase(Locale.ROOT);
            return astring;
        }

        public int getxToast() {
            return this.xToast;
        }

        public int getyToast() {
            return this.yToast;
        }

        public int getxTexture() {
            return this.xTexture;
        }

        public int getyTexture() {
            return this.yTexture;
        }

        public int getW() {
            return this.w;
        }

        public int getH() {
            return this.h;
        }
    }

    public ITextComponent getTitle() {
        return this.title;
    }

    public ITextComponent getSubtitle() {
        return this.subtitle;
    }

}
