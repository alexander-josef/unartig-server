/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Sep 22, 2005$
 *
 * Copyright (c) 2005 unartig AG  --  All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 *************************************************
 * $Log$
 * Revision 1.5  2007/03/14 03:18:36  alex
 * no more price segment
 *
 * Revision 1.4  2007/03/14 02:41:01  alex
 * initial checkin
 *
 * Revision 1.3  2007/03/13 16:55:03  alex
 * template for properties
 *
 * Revision 1.2  2007/03/09 23:44:24  alex
 * no message
 *
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.42  2006/12/06 18:42:08  alex
 * no js necessary for basic shopping cart functionality
 *
 * Revision 1.41  2006/12/05 22:51:56  alex
 * album kann jetzt freigeschaltet werden oder geschlossen sein
 *
 * Revision 1.40  2006/11/10 16:01:37  urban
 * tree bug solves
 * main.css
 *
 * Revision 1.39  2006/11/10 14:24:13  alex
 * dynamic priceinfo
 *
 * Revision 1.38  2006/11/08 09:55:03  alex
 * dynamic priceinfo
 *
 * Revision 1.37  2006/04/30 16:21:27  alex
 * removing system.outs
 *
 * Revision 1.36  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.35  2006/04/19 21:31:53  alex
 * session will be restored with album-bean (i.e. for bookmarked urls or so...)
 *
 * Revision 1.34  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.33  2006/03/20 15:45:33  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.32  2006/03/20 15:33:32  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.31  2006/03/08 17:42:26  alex
 * small fixes
 *
 * Revision 1.30  2006/03/03 16:54:56  alex
 * minor fixes
 *
 * Revision 1.29  2006/02/28 15:06:35  alex
 * link in overview links to first page
 *
 * Revision 1.28  2006/02/28 14:57:46  alex
 * added more resources (email for order confirmation), small fixes
 *
 * Revision 1.27  2006/02/23 14:37:42  alex
 * admin tool: new category works now
 *
 * Revision 1.26  2006/02/22 16:10:25  alex
 * added back link
 *
 * Revision 1.25  2006/02/22 14:00:51  alex
 * new album nav concept works also in display
 *
 * Revision 1.24  2006/02/16 17:13:46  alex
 * admin interface: deletion of levels works now
 *
 * Revision 1.23  2006/02/15 15:57:03  alex
 * bug [968] fixed. admin interface does that now
 *
 * Revision 1.22  2006/02/13 16:15:28  alex
 * but [968]
 *
 * Revision 1.21  2006/02/10 14:21:49  alex
 * admin tool: edit level partly ...
 *
 * Revision 1.20  2006/02/08 18:04:50  alex
 * first steps for album type configuration
 *
 * Revision 1.19  2006/01/24 15:38:35  alex
 * file ending is now .html
 *
 * Revision 1.18  2005/11/30 10:47:38  alex
 * bug fixes
 *
 * Revision 1.17  2005/11/29 02:00:17  alex
 * bug fixes
 *
 * Revision 1.16  2005/11/28 17:52:16  alex
 * bug fixes
 *
 * Revision 1.15  2005/11/27 19:39:10  alex
 * fast upload
 *
 * Revision 1.14  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.13  2005/11/25 10:56:58  alex
 *
 * Revision 1.12  2005/11/22 19:45:46  alex
 * admin actions, configurations
 *
 * Revision 1.11  2005/11/20 21:24:32  alex
 * side navigation fixes
 *
 * Revision 1.10  2005/11/19 11:08:20  alex
 * index navigation works, (extended GenericLevel functions)
 * wrong calculation of fixed checkout overview eliminated
 *
 * Revision 1.9  2005/11/18 19:15:52  alex
 * stuff ...
 *
 * Revision 1.8  2005/11/16 14:26:49  alex
 * validator works for email, new library
 *
 * Revision 1.7  2005/11/07 21:57:43  alex
 * admin interface refactored
 *
 * Revision 1.6  2005/11/07 17:38:26  alex
 * admin interface refactored
 *
 * Revision 1.5  2005/11/05 21:41:58  alex
 * overview und links in tree menu
 *
 * Revision 1.4  2005/10/26 14:34:32  alex
 * first version of album overview
 * new mappings in struts for the /album/** url
 *
 * Revision 1.3  2005/10/24 14:37:55  alex
 * small fixes, creating directories
 *
 * Revision 1.2  2005/10/24 13:50:07  alex
 * upload of album
 * import in db
 * processing of images
 *
 * Revision 1.1  2005/10/08 10:52:36  alex
 * jstl 1.1 integrated, new web.xml
 *
 * Revision 1.4  2005/10/06 14:30:09  alex
 * generating the nav tree recursivly works
 *
 * Revision 1.3  2005/10/06 11:06:33  alex
 * generating the nav tree
 *
 * Revision 1.2  2005/10/06 08:54:04  alex
 * cleaning up the model
 *
 * Revision 1.1  2005/09/26 18:37:45  alex
 * *** empty log message ***
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.exceptions.UnartigImagingException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.businesslogic.AlbumType;
import ch.unartig.studioserver.businesslogic.GenericLevelVisitor;
import ch.unartig.studioserver.imaging.ExifData;
import ch.unartig.studioserver.imaging.ImagingHelper;
import ch.unartig.studioserver.persistence.DAOs.*;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import ch.unartig.util.FileUtils;

import javax.media.jai.RenderedOp;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class StudioAlbum extends GeneratedStudioAlbum
{
    private Set problemFiles;
    /**
     * this String defines the action url that is to be called for viewing this album
     */
    private String actionString;
    /**
     * this will be overwritten be inheriting classes
     */
    private String actionStringPart = "/album/";

    /**
     * default constructor
     */
    public StudioAlbum()
    {
    }

    /**
     * Full Constructor
     * @param navTitle
     * @param longTitle
     * @param description
     * @param quickAccess
     * @param aPrivate
     * @param publish
     * @param privateAccessCode
     * @param albumAdvertisments
     * @param albumTypeString
     * @param event
     * @param photos
     * @param products
     */
    public StudioAlbum(String navTitle, String longTitle, String description, String quickAccess, Boolean aPrivate, boolean publish, String privateAccessCode, Set albumAdvertisments, String albumTypeString, Event event, Set photos, Set products) {
    }

    /**
     * actionString must be lazily initialized
     * <br>cannot be initialized during object creation ... null pointer exception
     *
     * @return action url string
     */
    private String lazyInitActionString()
    {
        if (actionString == null)
        {
            actionString = getActionStringPart() + getGenericLevelId().toString() + "/" + getNavTitle() + ".html";
        }
        _logger.debug("returning :" + actionString);
        return actionString;
    }

    public void accept(GenericLevelVisitor visitor)
    {
        try
        {
            visitor.visit(this);
        } catch (UnartigException e)
        {
            _logger.error("visitor threw exception", e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * @param navTitle
     * @param longTitle
     * @param description
     */
    public StudioAlbum(String navTitle, String longTitle, String description)
    {
        setNavTitle(navTitle);
        setLongTitle(longTitle);
        setDescription(description);
    }

    public StringBuffer composeTreeItem()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List listChildrenForNavTree()
    {
        return Collections.EMPTY_LIST;
    }

    public List listChildren()
    {
        return null;
    }

    public Class getParentClazz()
    {
        return Event.class;
    }

    public String[] getIndexNavEntry()
    {
        return new String[]{getIndexNavLink(), getNavTitle()};
    }

    public String getEventDateDisplay()
    {
        return getEvent().getEventDateDisplay();  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * convenience method to add an event
     *
     * @param event
     */
    public void setParentLevel(GenericLevel event)
    {
        _logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$  addParentLevel NARROW  $$$$$$$$$$$$$$$$$$$$$$$$4");
//        new Exception().printStackTrace();
        // todo: use parent level method from super ?
        Event e = (Event) event;
        setEvent(e);
        e.getStudios().add(this);
    }

    public GenericLevel getParentLevel()
    {
        return getEvent();
    }

    /**
     * set the hibernate - property albumTypeString using the AlbumType
     *
     * @param albumType
     */
    public void setAlbumType(AlbumType albumType)
    {
        this.setAlbumTypeString(albumType.getDesignator());
    }

    public AlbumType getAlbumType()
    {
        return AlbumType.getAlbumType(this.getAlbumTypeString());
    }

    /**
     * register all fine-photos in the db; reads the EXIF picture-take date and enters it for every photo record<br/>
     * needed: fine photos in appropriate location
     * <p/> This method shall fail gracefully with a exception message in case a photo can not be registered (corrupt file, not a foto-file etc.)
     *
     * @param createThumbDisp
     * @throws ch.unartig.exceptions.UnartigImagingException
     *
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     */
    public void registerPhotos(boolean createThumbDisp)
    {
        _logger.info("registerPhoto 1, " + System.currentTimeMillis());

        File[] photoFileArray = getFinePath().listFiles(new FileUtils.JpgFileFilter());
        String filename;
        Date pictureTakenDate;
        Integer pictureWidth;
        Integer pictureHeight;
        Set problemFiles = new HashSet();
        Double displayScale;
        Double thumbnailScale;

        int i;
        for (i = 0; i < photoFileArray.length; i++)
        {
            _logger.info("registerPhoto 2, " + System.currentTimeMillis());
            File photoFile = photoFileArray[i];
            try
            {

                RenderedOp fineImage = ImagingHelper.load(photoFile);

                ExifData exif = new ExifData(photoFile);
                pictureWidth = new Integer(fineImage.getWidth());
//            pictureWidth = ImagingHelper.getPixelsWidth(photoFile);
                pictureHeight = new Integer(fineImage.getHeight());
//            pictureHeight = ImagingHelper.getPixelsHeight(photoFile);
                _logger.debug("read width and height");
                Date exifDate = null;
                exifDate = exif.getPictureTakenDate();
                _logger.info("registerPhoto 3, " + System.currentTimeMillis());

                if (exifDate != null)
                {
                    pictureTakenDate = new Date(exifDate.getTime());
                } else
                {
                    pictureTakenDate = new Date(0);
                    _logger.error("unable to determine date of file : " + photoFile.getName());
                    problemFiles.add(photoFile);
                }
                filename = photoFile.getName();
                String displayTitle = filename;

                _logger.debug("filename : " + filename);
                _logger.debug("***********  " + pictureTakenDate + "  **************");

                Photo photo = new Photo(filename, this, pictureWidth, pictureHeight, pictureTakenDate, displayTitle);
                add(photo);
                // check if photo already exists?
                // copy file?
                // process: choose a (local?) directory with photos to import and a project
                // import routine checks if photo already exists
                // once imported thumbnailer will run ... (and the ones already 'thumbnailed' ?


                if (createThumbDisp)
                {
                    getDisplayPath().mkdirs();
                    getThumbnailPath().mkdirs();

                    // DISPLAY :
                    displayScale = new Double(Registry.getDisplayPixelsLongerSide().doubleValue() / new Double(ImagingHelper.getMaxWidthOrHightOf(fineImage)).doubleValue());
                    File displayFile = new File(getDisplayPath(), photoFile.getName());
                    ImagingHelper.createNewImage(fineImage, displayScale, displayFile, Registry._imageQuality, Registry._ImageSharpFactor);
                    _logger.info("wrote display " + displayFile.getName());

                    // THUMBNAIL:
                    thumbnailScale = new Double(Registry.getThumbnailPixelsLongerSide().doubleValue() / new Double(ImagingHelper.getMaxWidthOrHightOf(fineImage)).doubleValue());
                    File thumbnailFile = new File(getThumbnailPath(), photoFile.getName());
                    ImagingHelper.createNewImage(fineImage, thumbnailScale, thumbnailFile, Registry._imageQuality, Registry._ImageSharpFactor);
                    _logger.info("wrote thumbnail " + thumbnailFile.getName());
                }

            } catch (IOException e)
            {
                _logger.info("Problems while acessing the EXIF data of file " + photoFile.getName(),e);
                problemFiles.add(photoFile);
            } catch (UnartigImagingException e1)
            {
                _logger.info("Problem processing the image; continue with next image",e1);
                problemFiles.add(photoFile);
            } catch (UAPersistenceException e2)
            {
                _logger.info("Problem saving image; continue with next image",e2);
                problemFiles.add(photoFile);
            } catch (Exception e3)
            {
                _logger.info("unknown error; continue with next image",e3);
                problemFiles.add(photoFile);
            }
        }

        setProblemFiles(problemFiles);
    }


    /**
     * process all images in the fine folder and create:
     * - display folder and images
     * - thumbnail folder and images<br/>
     * Fine Folder must exist
     *
     * @throws ch.unartig.exceptions.UnartigException
     *
     */
    public void processImages() throws UnartigException
    {
        getDisplayPath().mkdirs();
        getThumbnailPath().mkdirs();
        // all fine fineImages:
        if (getFinePath() == null || !getFinePath().isDirectory())
        {
            throw new UnartigException("fine Path does not exist or is not directory");
        }


        _logger.debug("Going to process fineImages in directory : " + getFinePath().getAbsolutePath());
        File[] fineImages = getFinePath().listFiles(new FileUtils.JpgFileFilter());
        Double displayScale;
        Double thumbnailScale;
        for (int i = 0; i < fineImages.length; i++)
        {
            File image = fineImages[i];
            _logger.debug(" ... image : " + image.getName());
            RenderedOp fineImage = ImagingHelper.load(image);

            // DISPLAY :
            displayScale = new Double(Registry.getDisplayPixelsLongerSide().doubleValue() / new Double(ImagingHelper.getMaxWidthOrHightOf(fineImage)).doubleValue());
            File displayFile = new File(getDisplayPath(), image.getName());
            ImagingHelper.createNewImage(fineImage, displayScale, displayFile, Registry._imageQuality, Registry._ImageSharpFactor);

            // THUMBNAIL:
            thumbnailScale = new Double(Registry.getThumbnailPixelsLongerSide().doubleValue() / new Double(ImagingHelper.getMaxWidthOrHightOf(fineImage)).doubleValue());
            File thumbnailFile = new File(getThumbnailPath(), image.getName());
            ImagingHelper.createNewImage(fineImage, thumbnailScale, thumbnailFile, Registry._imageQuality, Registry._ImageSharpFactor);

        }

    }

    /**
     * overriden for album: go to the album-action
     *
     * @return album action link
     */
    public String getIndexNavLink()
    {
        return getActionString() + "?page=1";
    }

    /**
     * @return the action url that is to called for viewing this album
     */
    public String getActionLink()
    {
        return getActionString();
    }


    public String getLevelType()
    {
        return Registry._NAME_ALBUM_LEVEL_TYPE;
    }

    /**
     * convenience method to add and save a new photo that belongs to this album
     *
     * @param photo
     * @throws UAPersistenceException
     */
    private void add(Photo photo) throws UAPersistenceException
    {
        PhotoDAO phDao = new PhotoDAO();
        try
        {
            HibernateUtil.beginTransaction();
            getPhotos().add(photo);
            phDao.saveOrUpdate(photo);
            HibernateUtil.commitTransaction();
            _logger.info("wrote photo with id : " + photo.getPhotoId().toString());
        } catch (UAPersistenceException e)
        {
            HibernateUtil.rollbackTransaction();
            _logger.error("error while saving photo", e);
        } finally
        {
            HibernateUtil.finishTransaction();
        }

    }

    /**
     * returns the 'fine'-path for this album<br/>
     * creates all directories if needed
     *
     * @return a directory
     */
    public File getFinePath()
    {
        File albumPath = getAlbumPath();
        File finePath = new File(albumPath, Registry.getFinePath());
        if (!finePath.exists())
        {
            finePath.mkdirs();
        }
        return finePath;
    }

    private File getThumbnailPath()
    {
        File albumPath = getAlbumPath();
        return new File(albumPath, Registry.getThumbnailPath());
    }

    private File getDisplayPath()
    {
        File albumPath = getAlbumPath();
        return new File(albumPath, Registry.getDisplayPath());
    }

    private File getAlbumPath()
    {
        return new File(Registry.getDataPath(), getGenericLevelId().toString());
    }

    public void setProblemFiles(Set problemFiles)
    {
        this.problemFiles = problemFiles;
    }

    public Set getProblemFiles()
    {
        return problemFiles;
    }

    public List getPhotosAsList()
    {
        return new ArrayList(getPhotos());
    }

    /**
     * overridden from GenericLevel for checking the type of level
     *
     * @return true for album
     */
    public boolean isAlbumLevel()
    {
        return true;
    }

    /**
     * delete all photos. all order items that have a photo of this album need to set their photofilename and set the photoid to null
     */
    public void deleteLevel() throws UAPersistenceException
    {
        OrderItemDAO oiDao = new OrderItemDAO();

        Set orderItemsForAlbum = new HashSet();
        // todo!!!! performace of this approach most probably sucks big time ... create a HQL expression to update all orderitems
        for (Iterator iterator = getPhotos().iterator(); iterator.hasNext();)
        {
            Photo photo = (Photo) iterator.next();
            orderItemsForAlbum.addAll(photo.getOrderItems());
            photo.setOrderItems(Collections.EMPTY_SET);
        }


        for (Iterator iterator = orderItemsForAlbum.iterator(); iterator.hasNext();)
        {
            OrderItem orderItem = (OrderItem) iterator.next();
            orderItem.setPhotoFileName(orderItem.getPhoto().getFilename());
            orderItem.setPhoto(null);
            _logger.debug("trying to save order item : " + orderItem);
            try
            {
                oiDao.saveOrUpdate(orderItem);
            } catch (UAPersistenceException e)
            {
                throw new UAPersistenceException("can not save orderitem", e);
            }

        }


    }

    /**
     * convenience method to access last photo in album
     * todo: check how this method is used. currently it is also used to for the display view to test for the last photo (in which case no preview is needed)
     * todo: the term 'album' is not specific enough anymore with sportsalbums ... there, the last photo of one 'view' is not necessarily the last photo in the whole album
     * todo: override for sportsalbum?
     *
     * @return last photo in album
     * @throws ch.unartig.exceptions.UnartigException
     *
     */
    public Photo getLastPhotoInAlbumAndSelection() throws UnartigException
    {
        // todo: write new method in dao
        _logger.debug("StudioAlbum.getLastPhotoInAlbumAndSelection xxxx");
        // reload this album
        GenericLevelDAO glDao = new GenericLevelDAO();
        List photoList = new ArrayList(((StudioAlbum) glDao.load(this.getGenericLevelId(), StudioAlbum.class)).getPhotos());
        Photo retVal = (Photo) (photoList.get(getNumberOfPhotos() - 1));
        _logger.debug("returning photo : " + retVal);
        return retVal;
    }

    /**
     * method to get first photo in album
     *
     * @return first photo in album
     * @throws ch.unartig.exceptions.UnartigException
     *
     */
    public Photo getFirstPhotoInAlbum() throws UnartigException
    {
        PhotoDAO photoDao = new PhotoDAO();
        return photoDao.getFirstPhotoFor(this);
    }

    public String getActionString()
    {
        _logger.debug("calling action string ...");
        lazyInitActionString();
        return actionString;
    }

    public String getActionStringPart()
    {
        return actionStringPart;
    }

    public void setActionStringPart(String actionStringPart)
    {
        this.actionStringPart = actionStringPart;
    }


    /**
     * Given the map of productTypeids (key) and the priceids (value) set the collections of products for this album
     * @param productPrices
     * @throws ch.unartig.exceptions.UAPersistenceException
     */
    public void setProductPricesMap(Map productPrices) throws UAPersistenceException {

        // todo: make it simpler: create new set with all products and replace complete product set of album ????? Performance???
        // todo product already exists?
        // product entries per album: only one per productType
        Set productTypeIds = productPrices.keySet();
        PriceDAO priceDao = new PriceDAO();
        for (Iterator iterator = productTypeIds.iterator(); iterator.hasNext();)
        {
            String productTypeIdString = (String) iterator.next();
            String priceIdString = (String) productPrices.get(productTypeIdString);
            //producttypes with priceid <=0 are not set for this album
            final Long productTypeId = Long.valueOf(productTypeIdString);
            final Long priceId = Long.valueOf(priceIdString);
            if (priceId > 0 && !getAvailableProductTypes().keySet().contains(productTypeId))
            {
                // producttype does not yet exist for this album; create new product
                getProducts().add(new Product(productTypeId, priceId,this));
            }
            // product exists, and producttype is already available for album. update?
            else if (priceId > 0) {
                final Product availableProduct = getProductFor(productTypeId);
                final Price newPrice = priceDao.load(priceId);
                _logger.debug("available product : [" + availableProduct + "] old price: [" + availableProduct.getPrice() + "] new Price [" + newPrice + "]");
                availableProduct.setPrice(newPrice);
                // update newPrice
            } else {
                removeProductFor(productTypeId);
            }


        }
    }

    /**
     * if a product entry exists that has the passed productTypeId as identifier for its productType, delete it?
     * @param productTypeId
     * @return
     */
    private boolean removeProductFor(Long productTypeId) {
        return getProducts().remove(getProductFor(productTypeId));
    }

    /**
     * There is only one product for a product type in an album.
     * @param productTypeId The ID of the ProductType
     * @return the product that has the productType identified by the productTypeId or NULL, if no product exists with the given productType
     */
    public Product getProductFor(Long productTypeId) {
        // make a query or use the collection???
        for (Iterator iterator = getProducts().iterator(); iterator.hasNext();) {
            Product product = (Product) iterator.next();
            if (product.getProductType().getProductTypeId().equals(productTypeId)) {
                return product;
            }
        }
        _logger.debug("no product found with given productTypeId");
        return null;
    }

    /**
     * Return a map containing the productTypeId as key and the productType as Value.
     *
     * @return All products that are not set to inactive
     */
    public Map getAvailableProductTypes() {

        Map productTypeMap = new HashMap();
        for (Object o : getProducts()) {
            Product product = (Product) o;
            ProductType productType = product.getProductType();
            productTypeMap.put(productType.getProductTypeId(), productType);
        }
        return productTypeMap;
    }

    /**
     * Only products that are not flagged for inactive shall be shown in the product information or shopping cart.
     * @return Active products (products that don't have the inactvie flag)
     */
    public Set getActiveProducts()
    {
        Set<Product> activeProducts = new HashSet<Product>();
        Set allProductsForAlbum = getProducts();
        for (Object anAllProductsForAlbum : allProductsForAlbum) {
            Product product = (Product) anAllProductsForAlbum;
            if (product.getInactive() ==null || !product.getInactive()) { // either null or NOT inactive
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }
    
}
